package com.capsuleclash.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestMoves {

	@Test
	public void test() {
		Moves moves = new Moves(0);
		List<Step> steps = new ArrayList<>();
		steps.add(new Step(0, 0));

		assertTrue(moves.getList().get(0).equalsStep(steps.get(0)));
		
		moves = new Moves(1);
		steps = new ArrayList<>();

		steps.add(new Step(-1, 0));
		steps.add(new Step(0, -1));
		steps.add(new Step(0, 0));
		steps.add(new Step(0, 1));
		steps.add(new Step(1, 0));
		
		assertTrue(moves.getList().get(0).equalsStep(steps.get(0)));
		assertTrue(moves.getList().get(1).equalsStep(steps.get(1)));
		assertTrue(moves.getList().get(2).equalsStep(steps.get(2)));
		assertTrue(moves.getList().get(3).equalsStep(steps.get(3)));
		assertTrue(moves.getList().get(4).equalsStep(steps.get(4)));

	}

}
