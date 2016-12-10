package com.capsuleclash.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTeam {

	@Test
	public void testTotalHealth() {
		System.setProperty("user.dir", System.getProperty("user.dir") + "/assets/");
		FigureCollection fc = FigureCollection.getInstance();

		Figure fig = fc.getFigure(0);
		Figure fig2 = fc.getFigure(1);
		
		Team team = new Team();
		assertEquals(0, team.totalHealth());
		team.add(fig);
		assertEquals(3, team.totalHealth());
		team.add(fig2);
		assertEquals(9, team.totalHealth());
		team.remove(fig2);
		assertEquals(3, team.totalHealth());
	}

}
