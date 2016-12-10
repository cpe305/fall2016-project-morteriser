package com.capsuleclash.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStandardAction {

	@Test
	public void testCalculate() {
		System.setProperty("user.dir", System.getProperty("user.dir") + "/assets/");
		FigureCollection fc = FigureCollection.getInstance();
		
		Figure fig = fc.getFigure(0);
		StandardAction stand = new StandardAction();
		assertEquals(1, stand.calculate(fig, fc.getFigure(0)));
		assertEquals(2, stand.calculate(fig, fc.getFigure(2)));
		
		assertEquals("Standard", stand.toString());
	}
}
