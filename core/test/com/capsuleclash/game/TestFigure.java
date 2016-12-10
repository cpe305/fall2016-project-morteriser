package com.capsuleclash.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFigure {

	@Test
	public void testCompareTo() {
		System.setProperty("user.dir", System.getProperty("user.dir") + "/assets/");
		FigureCollection fc = FigureCollection.getInstance();

		Figure fig = fc.getFigure(0);
		Figure fig2 = fc.getFigure(1);
		assertEquals(-1, fig.compareTo(fig2));
		assertEquals(1, fig2.compareTo(fig));
		assertEquals(0, fig.compareTo(fc.getFigure(0)));
	}

	@Test
	public void testTakeDamage() {
		System.setProperty("user.dir", System.getProperty("user.dir") + "/assets/");
		FigureCollection fc = FigureCollection.getInstance();

		Figure fig = fc.getFigure(0);
		
		assertEquals(3, fig.getHealth());
		fig.takeDamage(1);
		assertEquals(2, fig.getHealth());
		fig.takeDamage(4);
		assertEquals(0, fig.getHealth());
	}

	@Test
	public void testEquals() {
		System.setProperty("user.dir", System.getProperty("user.dir") + "/assets/");
		FigureCollection fc = FigureCollection.getInstance();
		
		Figure fig = fc.getFigure(0);
		Figure fig2 = fc.getFigure(1);
		assertTrue(fig.equals(fc.getFigure(0)));
		assertFalse(fig.equals(fig2));
		assertEquals(fig.toString(), fc.getFigure(0).toString());
	}
}
