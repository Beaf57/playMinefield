package br.com.beatrizdev.minefield.ui;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.beatrizdev.minefield.exception.ExitException;
import br.com.beatrizdev.minefield.exception.ExplosionException;
import br.com.beatrizdev.minefield.model.Board;

public class ConsoleBoard {

	private Board board;
	private Scanner entry = new Scanner(System.in);
	
	public ConsoleBoard(Board board) {
		this.board = board;
		
		runGame();
	}

	private void runGame() {	
		try {
			boolean proceed = true;
			
			while(proceed) {
				loopGame();
				
				System.out.println("Outra partida? (S/n) ");
				String answer = entry.nextLine();
				
				if("n".equalsIgnoreCase(answer)) {
					proceed = false;
				} else {
					board.restart();
				}
			}
			
		}catch(ExitException e) {
			System.out.println("Tchau!!");
			
		} finally{
			entry.close();
		}
	}

	private void loopGame() {
		try {
			
			while(!board.objectiveAchieved()) {
				System.out.println(board);
				String typed = capturedTypedValue("Digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(typed.split(","))
						.map(e -> Integer.parseInt(e.trim())).iterator();
				
				typed = capturedTypedValue("1 - Abrir ou 2 - (Des) marcar: ");
				
				if("1".equals(typed)) {
					board.toOpen(xy.next(), xy.next());
				} else if("2".equals(typed)){
					board.toggleMarkup(xy.next(), xy.next());
				}
			}
			
			System.out.println(board);
			System.out.println("Você ganhou!!");
		} catch(ExplosionException e) {
			System.out.println(board);
			System.out.println("Você perdeu!");
		}
		
	}
	
	private String capturedTypedValue(String text) {
		System.out.print(text);
		String typed = entry.nextLine();
		
		if("sair".equalsIgnoreCase(typed)) {
			throw new ExitException();
		}
		return typed;
	}
}
