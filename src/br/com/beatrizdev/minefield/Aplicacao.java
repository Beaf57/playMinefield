package br.com.beatrizdev.minefield;

import br.com.beatrizdev.minefield.model.Board;
import br.com.beatrizdev.minefield.ui.ConsoleBoard;

public class Aplicacao {
 public static void main(String[] args) {
	 
	 Board board = new Board(6, 6, 4);
	 new ConsoleBoard(board);
	 
  }
}
