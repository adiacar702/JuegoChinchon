package rules;

import java.util.*;

import resources.Card;
import utils.CombinationResult;

/**
 * Valida combinaciones del juego (tríos, escaleras y chinchón).
 */
public class CombinationValidator implements HandEvaluator {

	private final Combinations DETECTOR = new Combinations();

	/**
	 * Analiza una mano completa y devuelve la mejor combinación posible.
	 * 
	 * @param hand mano del jugador
	 * @return resultado con tríos, escaleras y cartas no válidas
	 */

	public CombinationResult analyzeHand(List<Card> hand) {

		CombinationResult result, r1, r2;
		// CHINCHÓN
		if (DETECTOR.isChinchon(hand)) {
			result = new CombinationResult();
			result.setChinchon(true);

		} else {
			r1 = analyzeEscalerasFirst(hand);
			r2 = analyzeTriosFirst(hand);

			result = r1.getRemaining()
					.size() <= r2.getRemaining()
							.size() ? r1 : r2;
		}
		return result;
	}

	/**
	 * Cuenta cuántas cartas forman combinaciones válidas.
	 * 
	 * @param hand mano del jugador
	 * @return número de cartas válidas
	 */

	public int countValidCards(List<Card> hand) {
		CombinationResult result = analyzeHand(hand);
		int valids;
		valids = hand.size() - result.getRemaining()
				.size();
		return valids;
	}

	/**
	 * Calcula los puntos de las cartas no válidas.
	 * 
	 * @param hand mano del jugador
	 * @return suma de valores de cartas no combinadas
	 */
	@Override
	public int calculateNoValidNums(List<Card> hand) {
		int calc = analyzeHand(hand).getRemaining()
				.stream()
				.mapToInt(Card::getNumericValue)
				.sum();
		return calc;
	}

	/**
	 * Determina si un jugador puede cerrar la ronda.
	 * 
	 * @param hand mano del jugador
	 * @return true si puede cerrar
	 */
	public boolean canClose(List<Card> hand) {

		CombinationResult result = analyzeHand(hand);
		boolean close;
		int invalids, value;
		if (result.isChinchon()) {
			close = true;
		} else {
			invalids = result.getRemaining()
					.size();
			value = result.getRemaining()
					.stream()
					.mapToInt(Card::getNumericValue)
					.sum();

			close = invalids == 1 && value <= 5;
		}
		return close;
	}

	// Analiza combinaciones de trios antes que escaleras
	private CombinationResult analyzeTriosFirst(List<Card> hand) {

		CombinationResult result = new CombinationResult();

		List<Card> remaining = new ArrayList<>(hand);

		List<List<Card>> trios = DETECTOR.findTrios(remaining);
		result.getTrios()
				.addAll(trios);
		trios.forEach(remaining::removeAll);

		List<List<Card>> escalas = DETECTOR.findEscaleras(remaining);
		result.getEscalas()
				.addAll(escalas);
		escalas.forEach(remaining::removeAll);

		result.getRemaining()
				.addAll(remaining);

		return result;
	}

	// Analiza combinaciones de escaleras antes que trios
	private CombinationResult analyzeEscalerasFirst(List<Card> hand) {

		CombinationResult result = new CombinationResult();
		List<Card> remaining = new ArrayList<>(hand);
		List<List<Card>> escalas = DETECTOR.findEscaleras(remaining);
		result.getEscalas()
				.addAll(escalas);
		escalas.forEach(remaining::removeAll);

		List<List<Card>> trios = DETECTOR.findTrios(remaining);
		result.getTrios()
				.addAll(trios);
		trios.forEach(remaining::removeAll);
		result.getRemaining()
				.addAll(remaining);

		return result;
	}

}