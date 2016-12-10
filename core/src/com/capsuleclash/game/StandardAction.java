package com.capsuleclash.game;

/**
 * A type of Action that calculates the damage done by subtracting the target defense from
 * the user's attack.
 * @author Hristo
 */
public class StandardAction implements Action {

	@Override
	public int calculate(Figure user, Figure target) {
		int damage = user.getAttack() - target.getDefense();
		if (damage < 1) {
			damage = 1;
		}
		target.takeDamage(damage);
		return damage;
	}
	
	/**
	 * Returns the name of this action.
	 */
	@Override
	public String toString() {
		return "Standard";
	}

}
