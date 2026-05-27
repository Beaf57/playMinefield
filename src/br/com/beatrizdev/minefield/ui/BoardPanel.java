package br.com.beatrizdev.minefield.ui;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.beatrizdev.minefield.model.Board;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	public BoardPanel(Board board) {
		
		setLayout(new GridLayout(board.getLines(), board.getColumns()));
		
		board.forEach(f -> add(new FieldButton(f)));
		
		board.registerObservers(e -> {
			SwingUtilities.invokeLater(() -> {
				if(e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Ganhou :)");
				} else {
					JOptionPane.showMessageDialog(this, "Perdeu :(");
				}
				board.restart();
			});	
		});
	}
}
