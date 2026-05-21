package br.com.beatrizdev.minefield;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.beatrizdev.minefield.exception.ExplosionException;
import br.com.beatrizdev.minefield.model.Field;

public class TestField {
	
	private Field field;
	
	@BeforeEach
	void startField() {
		field = new Field(3, 3);
	}

	@Test
	void testNeighborDistance1Left() {
		Field neighbor = new Field(3, 2);
		boolean result = field.addNeighbor(neighbor);
		assertTrue(result);
	}
	
	@Test
	void testNeighborDistance1Right() {
		Field neighbor = new Field(3, 4);
		boolean result = field.addNeighbor(neighbor);
		assertTrue(result);
	}
	
	@Test
	void testNeighborDistance1OnTop() {
		Field neighbor = new Field(2, 3);
		boolean result = field.addNeighbor(neighbor);
		assertTrue(result);
	}
	
	@Test
	void testNeighborDistance1Below() {
		Field neighbor = new Field(4, 3);
		boolean result = field.addNeighbor(neighbor);
		assertTrue(result);
	}
	
	@Test
	void testNeighborDistance2() {
		Field neighbor = new Field(2, 2);
		boolean result = field.addNeighbor(neighbor);
		assertTrue(result);
	}
	
	@Test
	void testNotNeighbor() {
		Field neighbor = new Field(1, 1);
		boolean result = field.addNeighbor(neighbor);
		assertFalse(result);
	}
	
	@Test
	void defaultValueTestMarkedAttribute() {
		assertFalse(field.isMarked());
	}
	
	@Test
	void testToggleMarkup() {
		field.toggleMarkup();
		assertTrue(field.isMarked());
	}
	
	@Test
	void testSwitchMarkingTwoCalls() {
		field.toggleMarkup();
		field.toggleMarkup();
		assertFalse(field.isMarked());
	}
	
	@Test
	void testOpenNotMinedAndNotMarked() {
		assertTrue(field.toOpen());
	}
	
	@Test
	void testOpenNotMinedAndMarked() {
		field.toggleMarkup();
		assertFalse(field.toOpen());
	}
	
	@Test
	void testOpenMinedAndMarked() {
		field.toggleMarkup();
		field.toMine();
		assertFalse(field.toOpen());
	}
	
	@Test
	void testMinedAndNotMarked() {
		field.toMine();
		assertThrows(ExplosionException.class, () -> {
			field.toOpen();
		});
	}
	
	@Test
	void testOpenWithNeighbors1() {
		Field field11 = new Field(1, 1);
		
		Field field22 = new Field(2, 2);
		field22.addNeighbor(field11);
		
		field.addNeighbor(field22);
		
		field.toOpen();
		
		assertTrue(field22.isOpen() && field11.isOpen());
	}
	
	@Test
	void testOpenWithNeighbors2() {
		
		Field field11 = new Field(1, 1);
		Field field12 = new Field(1, 2);
		field12.toMine();
		
		Field field22 = new Field(2, 2);
		field22.addNeighbor(field11);
		field22.addNeighbor(field12);
		
		field.addNeighbor(field22);
		field.toOpen();
		
		assertTrue(field22.isOpen() && !field11.isOpen());
	}
}

