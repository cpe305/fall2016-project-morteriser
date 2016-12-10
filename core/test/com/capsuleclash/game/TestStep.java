package com.capsuleclash.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStep {

	@Test
	public void testEqualsStep() {
		Step step = new Step(1, 1);
		Step step2 = new Step(1, 2);
		
		assertFalse(step.equalsStep(step2));
		Step step3 = new Step(1, 1);
		assertTrue(step.equalsStep(step3));
	}

}
