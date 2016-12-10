package com.capsuleclash.game;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;
/**
 * I don't think unit tests are possible for this, only really integration tests.
 */
public class TestBoard {

	@Test
	public void testBounds() {
		Point p = new Point(0, 0);
		Board b = new Board();
		
		assertTrue(b.inBounds(p));
		p = new Point(-1, 0);
		assertFalse(b.inBounds(p));
	}

}
