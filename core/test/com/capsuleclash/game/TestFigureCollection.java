package com.capsuleclash.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFigureCollection {

	@Test
	public void testGetInstance() {
		System.setProperty("user.dir", System.getProperty("user.dir") + "/assets/");
		FigureCollection fc = FigureCollection.getInstance();
		ActionFactory af = new ActionFactory();
		
		Figure temp = new Figure(0, 3, 2, 2, 2, "Knight", af.getAction("standard"));
		assertEquals(fc.getFigure(0).toString(), temp.toString());
	}

}
