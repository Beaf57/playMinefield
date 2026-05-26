package br.com.beatrizdev.minefield.ui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.beatrizdev.minefield.model.EventField;
import br.com.beatrizdev.minefield.model.Field;
import br.com.beatrizdev.minefield.model.ObserverField;

@SuppressWarnings("serial")
public class FieldButton extends JButton implements ObserverField, MouseListener {

	private final Color BG_STANDARD = new Color(184, 184, 184);
	private final Color BG_MARKED = new Color(8, 179, 247);
	private final Color BG_BLOWUP = new Color(189, 66, 68);
	private final Color TEXT_GREEN = new Color(0, 100, 0);
	
	private Field field;
	
	public FieldButton(Field field) {
		this.field = field;
		setBackground(BG_STANDARD);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(this);
		field.registerObserver(this);	
	}

	@Override
	public void eventOccurred(Field c, EventField event) {
		
		switch(event) {
		case OPEN: 
			applyStyleOpen();
			break;
		case MARKED: 
			applyStyleMarked();
			break;
		case BLOWUP:
			applyStyleBlowup();
			break;
		default: 
			applyStandardStyle();
		}
		
	}

	private void applyStandardStyle() {
		setBackground(BG_STANDARD);
		setText("");
	}

	private void applyStyleBlowup() {
		setBackground(BG_BLOWUP);
		setForeground(Color.WHITE);
		setText("X");
	}

	private void applyStyleMarked() {
		setBackground(BG_MARKED);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void applyStyleOpen() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(field.isMined()) {
			setBackground(BG_BLOWUP);
			return;
		}
		
		setBackground(BG_STANDARD);
		
		
		switch(field.minesInTheNeighborhood()) {
		case 1:
			setForeground(TEXT_GREEN);
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);
		}
		
		String value = !field.safeNeighborhood() ? field.minesInTheNeighborhood() + "" : "";
		setText(value);
	}
	
	
	//Interface dos eventos do mouse

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1) {
			field.toOpen();
		} else {
			field.toggleMarkup();
		}
		
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
