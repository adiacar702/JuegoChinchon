package rules;

import java.util.List;

import resources.Card;

/**
 * Interfaz para que la ia pueda evaluar el contenido de su mano
 */
public interface HandEvaluator {
	int calculateNoValidNums(List<Card> hand);
}