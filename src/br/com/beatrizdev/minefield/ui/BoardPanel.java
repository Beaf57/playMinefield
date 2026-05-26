package br.com.beatrizdev.minefield.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import br.com.beatrizdev.minefield.model.Board;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	public BoardPanel(Board board) {
		
		setLayout(new GridLayout(board.getLines(), board.getColumns()));
		
		board.forEach(f -> add(new FieldButton(null)));
		
		board.registerObservers(e -> {
			//TODO mostrar resultados para o usuário!
		});
	}
}
