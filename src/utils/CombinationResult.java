package utils;

import java.util.*;

import resources.Card;

public class CombinationResult {

	private List<List<Card>> trios = new ArrayList<>();
	private List<List<Card>> escalas = new ArrayList<>();
	private boolean chinchon;
	private List<Card> remaining = new ArrayList<>();

	public List<List<Card>> getTrios() {
		return trios;
	}

	public List<List<Card>> getEscalas() {
		return escalas;
	}

	public boolean isChinchon() {
		return chinchon;
	}

	public List<Card> getRemaining() {
		return remaining;
	}

	public void setChinchon(boolean chinchon) {
		this.chinchon = chinchon;
	}

}
