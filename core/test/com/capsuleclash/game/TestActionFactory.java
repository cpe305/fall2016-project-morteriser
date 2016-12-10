package com.capsuleclash.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestActionFactory {

	@Test
	public void test() {
		ActionFactory af = new ActionFactory();
		
		assertEquals("Standard", af.getAction("standard").toString());
		assertEquals("Pierce", af.getAction("pierce").toString());
	}

}
