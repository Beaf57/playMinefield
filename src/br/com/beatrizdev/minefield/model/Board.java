package br.com.beatrizdev.minefield.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Board {

	private int lines;
	private int columns;
	private int mines;
	
	private final List<Field> fields = new ArrayList<>();
	
	public Board(int lines, int columns, int mines) {
		this.lines = lines;
		this.columns = columns;
		this.mines = mines;
		
		generateFields();
		connectWithNeighbors();
		drawMines();
	}
	
	public void toOpen(int line, int column) {
		try {
			fields.parallelStream()
			.filter(f -> f.getLine() == line && f.getColumn() == column)
			.findFirst()
			.ifPresent(f -> f.toOpen());
		} catch(Exception e) {
			//FIXME Ajustar a implementação do método abrir
			fields.forEach(f -> f.setOpen(true));
			throw e;
		}
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
				fields.add(new Field(line, column));
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
	

}