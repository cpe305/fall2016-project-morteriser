package com.capsuleclash.game;

import java.util.ArrayList;
import java.util.Collections;

public class Moves {
	private ArrayList<Step> moves;

	public Moves(int spacesCanMove) {
		moves = new ArrayList<Step>();
		for (int x = -spacesCanMove; x <= spacesCanMove; x++) {
			for (int y = -spacesCanMove + Math.abs(x); 
					y <= spacesCanMove - Math.abs(x); y++) {
				add(new Step(x, y));
				//System.out.print("x: " + x + " y:" + y + " ");
			}
			//System.out.println();
		}
	}
	
	public ArrayList<Step> getList() {
		return moves;
	}

	public void add(Step x) {
		moves.add(x);
	}

	public void remove(Step x) {
		moves.remove(x);
	}
}
