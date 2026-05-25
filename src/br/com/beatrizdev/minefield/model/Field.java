package br.com.beatrizdev.minefield.model;

import java.util.ArrayList;
import java.util.List;

import br.com.beatrizdev.minefield.exception.ExplosionException;

public class Field {

	private final int line;
	private final int column;
	
	private boolean open = false;
	private boolean mined = false;
	private boolean marked = false;
	
	private List<Field> neighbors = new ArrayList<>();
	
	public Field(int line, int column) {
		super();
		this.line = line;
		this.column = column;
	}
	
	public boolean addNeighbor(Field neighbor) {
		boolean differentLine  = line != neighbor.line;
		boolean differentColumn = column != neighbor.column;
		boolean diagonal = differentLine && differentColumn;
		
		int deltaLine = Math.abs(line - neighbor.line);
		int deltaColumn = Math.abs(column - neighbor.column);
		int generalDelta = deltaColumn + deltaLine;
		
		if(generalDelta == 1 && !diagonal) {
			neighbors.add(neighbor);
			return true;
		} else if(generalDelta == 2 && diagonal) {
			neighbors.add(neighbor);
			return true;
		} else {
			return false;
		}
	}
	
	public void toggleMarkup() {
		if(!open) {
			marked = !marked;
		}
	}
	
	public boolean toOpen() {
		
		if(!open && !marked) {
			open = true;
			
			if(mined) {
				//TODO Implementar nova versão
			}
			if(safeNeighborhood()) {
				neighbors.forEach(v -> v.toOpen());
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean safeNeighborhood() {
		return neighbors.stream().noneMatch(v -> v.mined);			
	}
	
	public void toMine() {
		mined = true;
	}
	
	public boolean isMined() {
		return mined;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean isOpen() {
		return open;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}
	
	public boolean objectiveAchieved() {
		boolean unveiled = !mined && open;
		boolean sheltered = mined && marked;
		return unveiled || sheltered;
	}
	
	public long minesInTheNeighborhood() {
		return neighbors.stream().filter(v -> v.mined).count();
	}
	
	public void restart() {
		open = false;
		mined = false;
		marked = false;
	}
}


