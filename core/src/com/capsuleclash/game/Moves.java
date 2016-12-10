package com.capsuleclash.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the moves that can be made over the grid as a list of steps.
 * @author Hristo
 *
 */
public class Moves {
	private List<Step> movesToMake;

	/**
	 * Creates a list of steps based on the amount of tiles something can move.
	 * @param spacesCanMove amount of tiles a figure can move.
	 */
	public Moves(int spacesCanMove) {
		movesToMake = new ArrayList<Step>();
		for (int x = -spacesCanMove; x <= spacesCanMove; x++) {
			for (int y = -spacesCanMove + Math.abs(x); 
					y <= spacesCanMove - Math.abs(x); y++) {
				add(new Step(x, y));
			}
		}
	}
	
	/**
	 * Gets the list of Steps.
	 * @return list of Steps.
	 */
	public List<Step> getList() {
		return movesToMake;
	}

	/** 
	 * Adds a Step.
	 * @param x step to be added.
	 */
	public void add(Step x) {
		movesToMake.add(x);
	}

	/**
	 * Removes a Step.
	 * @param x step to be removed.
	 */
	public void remove(Step x) {
		movesToMake.remove(x);
	}
}
