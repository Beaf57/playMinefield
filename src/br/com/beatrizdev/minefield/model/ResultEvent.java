package br.com.beatrizdev.minefield.model;

public class ResultEvent {

	private final boolean ganhou;

	public ResultEvent(boolean ganhou) {
		super();
		this.ganhou = ganhou;
	}

	public boolean isGanhou() {
		return ganhou;
	}
}
