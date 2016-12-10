package com.capsuleclash.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPierceAction {

	@Test
	public void testCalculate() {
		System.setProperty("user.dir", System.getProperty("user.dir") + "/assets/");
		FigureCollection fc = FigureCollection.getInstance();
		
		Figure fig = fc.getFigure(0);
		PierceAction stand = new PierceAction();
		assertEquals(2, stand.calculate(fig, fc.getFigure(0)));
		assertEquals(2, stand.calculate(fig, fc.getFigure(2)));
		
		assertEquals("Pierce", stand.toString());
	}

}
