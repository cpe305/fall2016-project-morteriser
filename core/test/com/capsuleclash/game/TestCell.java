package com.capsuleclash.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCell {

	@Test
	public void testPlaceUnit() {
		System.setProperty("user.dir", System.getProperty("user.dir") + "/assets/");
		FigureCollection fc = FigureCollection.getInstance();
		
		Figure fig = fc.getFigure(0);
		
		Cell cell = new Cell();
		assertEquals(null, cell.getFigure());
		cell.placeUnit(fig, Cell.State.UNITP1);
		assertEquals(fc.getFigure(0), cell.getFigure());
		cell.removeUnit();
		assertEquals(null, cell.getFigure());
	}

}
