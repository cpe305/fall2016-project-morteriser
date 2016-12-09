package com.capsuleclash.game;

public class StandardAction implements Action {

	public StandardAction() {

	}

	@Override
	public int calculate(Figure user, Figure target) {
		int damage = user.getAttack() - target.getDefense();
		if (damage < 1) {
			damage = 1;
		}
		target.takeDamage(damage);
		return damage;
	}
	
	public String toString() {
		return "Standard";
	}

}
