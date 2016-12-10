package com.capsuleclash.game;

/**
 * @author Hristo Stoytchev
 *
 * To implement as Strategy pattern.
 */
@FunctionalInterface
public interface Action {
	/**
	 * Calculates the amount of damage an enemy will take.
	 * @param user the unit using the attack.
	 * @param target the unit receiving the attack.
	 * @return the amount of the damage the target will take.
	 */
	public int calculate(Figure user, Figure target);
}
