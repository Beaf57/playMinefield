package br.com.beatrizdev.minefield;

import br.com.beatrizdev.minefield.model.Board;

public class Aplicacao {
 public static void main(String[] args) {
	 
	 Board board = new Board(6, 6, 6);
	 System.out.println(board);
	 
	 board.toOpen(3, 3);
	 board.toggleMarkup(4, 4);
	 board.toggleMarkup(4, 5);
	 
	 System.out.println(board);
  }
}
