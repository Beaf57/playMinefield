package br.com.beatrizdev.minefield.ui;

import br.com.beatrizdev.minefield.model.Board;

public class Temp {
	public static void main(String[] args) {
		Board board = new Board(3, 3, 9);
		board.toggleMarkup(0, 0);
		board.toggleMarkup(0, 1);
		board.toggleMarkup(0, 2);
		board.toggleMarkup(1, 0);
		board.toggleMarkup(1, 1);
		board.toggleMarkup(1, 2);
		board.toggleMarkup(2, 0);
		board.toggleMarkup(2, 1);
		board.toggleMarkup(2, 2);
	}
}
