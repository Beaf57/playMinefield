package br.com.beatrizdev.minefield.model;

@FunctionalInterface
public interface ObserverField {

	public void eventOccurred(Field c, EventField event);
}
