package br.com.beatrizdev.minefield.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.beatrizdev.minefield.model.EventField;
import br.com.beatrizdev.minefield.model.Field;
import br.com.beatrizdev.minefield.model.ObserverField;

@SuppressWarnings("serial")
public class FieldButton extends JButton implements ObserverField {

	private final Color BG_STANDARD = new Color(184, 184, 184);
	private final Color BG_MARKED = new Color(8, 179, 247);
	private final Color BG_BLOWUP = new Color(189, 66, 68);
	private final Color TEXT_GREEN = new Color(0, 100, 0);
	
	private Field field;
	
	public FieldButton(Field field) {
		this.setField(field);
		setBackground(BG_STANDARD);
		setBorder(BorderFactory.createBevelBorder(0));
		
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
		// TODO Auto-generated method stub
		
	}

	private void applyStyleBlowup() {
		// TODO Auto-generated method stub
		
	}

	private void applyStyleMarked() {
		// TODO Auto-generated method stub
		
	}

	private void applyStyleOpen() {
		// TODO Auto-generated method stub
		
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}
