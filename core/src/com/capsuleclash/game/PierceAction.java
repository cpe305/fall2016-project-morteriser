package com.capsuleclash.game;

/**
 * A special type of Action that ignores the target's defense.
 * @author Hristo
 */
public class PierceAction implements Action {

	@Override
	public int calculate(Figure user, Figure target) {
		int damage = user.getAttack();
		target.takeDamage(damage);
		return damage;
	}
	
	/**
	 * Returns the name of this action.
	 */
	@Override
	public String toString() {
		return "Pierce";
	}

}
