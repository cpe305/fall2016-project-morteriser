package com.capsuleclash.game;

import java.io.Writer;

/**
 * 
 * @author Hristo Stoytchev
 * 
 * A version of the main game to be displayed on console.
 *
 */
public class ConsoleGame implements Observer {

	private Writer writer;
	
	public ConsoleGame(Writer in) {
		this.writer = in;
	}
	
	@Override
	public void update(Subject s) {
		
	}

}
