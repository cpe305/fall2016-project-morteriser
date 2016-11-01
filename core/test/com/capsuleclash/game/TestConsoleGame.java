package com.capsuleclash.game;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.Test;

public class TestConsoleGame {
	private ConsoleGame console;

	@Test
	public void testUpdate() {
		try {
			PrintWriter myWriter = new PrintWriter(new File("test.out"));
			console = new ConsoleGame(myWriter);
			//incomplete
			assertTrue(true);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
