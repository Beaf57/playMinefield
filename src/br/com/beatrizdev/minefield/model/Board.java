package br.com.beatrizdev.minefield.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Board implements ObserverField {

	private int lines;
	private int columns;
	private int mines;
	
	private final List<Field> fields = new ArrayList<>();
	private final List<Consumer<Boolean>> observers = new ArrayList<>();
	
	public Board(int lines, int columns, int mines) {
		this.lines = lines;
		this.columns = columns;
		this.mines = mines;
		
		generateFields();
		connectWithNeighbors();
		drawMines();
	}
	
	public void forEach(Consumer<Field> function) {
		fields.forEach(function);
	}
	
	public void registerObservers(Consumer<Boolean> observer) {
		observers.add(observer);
	}
	
	private void notifyObservers(boolean result) {
		observers.stream()
			.forEach(o -> o.accept(result));
	}
	
	public void toOpen(int line, int column) {
		
			fields.parallelStream()
			.filter(f -> f.getLine() == line && f.getColumn() == column)
			.findFirst()
			.ifPresent(f -> f.toOpen());
	}
	
	public void toggleMarkup(int line, int column) {
		fields.parallelStream()
			.filter(f -> f.getLine() == line && f.getColumn() == column)
			.findFirst()
			.ifPresent(f -> f.toggleMarkup());
	}
	private void generateFields() {
		for(int line = 0; line < lines; line++) {
			for(int column = 0; column < columns; column++) {
				Field field = new Field(line, column);
				field.registerObserver(this);
				fields.add(field);
			}
		}
	}
	private void connectWithNeighbors() {
		for(Field f1: fields) {
			for(Field f2: fields) {
				f1.addNeighbor(f2);
			}
		}
	}
	
	private void drawMines() {
		long armedMines = 0;
		Predicate<Field> mined = f -> f.isMined();
		do {
			int random = (int) (Math.random() * fields.size());
			fields.get(random).toMine();
			armedMines = fields.stream().filter(mined).count();
		} while(armedMines < mines);
	}
	
	public boolean objectiveAchieved() {
		return fields.stream().allMatch(f -> f.objectiveAchieved());
	}
	
	public void restart() {
		fields.stream().forEach(f -> f.restart());
		drawMines();
	}

	public int getLines() {
		return lines;
	}

	public int getColumns() {
		return columns;
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<Consumer<Boolean>> getObservers() {
		return observers;
	}

	@Override
	public void eventOccurred(Field c, EventField event) {
		if(event == EventField.BLOWUP) {
			showMines();
			notifyObservers(false);
		} else if(objectiveAchieved()) {
			notifyObservers(true);
		}
		
	}
	
	private void showMines() {
		fields.stream()
			.filter(f -> f.isMined())
			.forEach(f -> f.setOpen(true));
	}
	

}