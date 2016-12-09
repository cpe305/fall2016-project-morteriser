package com.capsuleclash.game;

public class PierceAction implements Action {

	public PierceAction() {

	}

	@Override
	public int calculate(Figure user, Figure target) {
		int damage = user.getAttack();
		target.takeDamage(damage);
		return damage;
	}
	
	public String toString() {
		return "Pierce";
	}

}
