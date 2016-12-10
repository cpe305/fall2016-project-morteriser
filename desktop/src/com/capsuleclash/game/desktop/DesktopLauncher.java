package com.capsuleclash.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.capsuleclash.game.*;

/**
 * The launcher for the desktop application that libgdx starts.
 * @author Hristo
 *
 */
public class DesktopLauncher {
	/**
	 * Prevent instantiation of class.
	 */
	private DesktopLauncher() {
		
	}
	/**
	 * Begins the game with a window size title and puts the Board and MainGame together.
	 * @param arg command line arguments.
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Capsule Clash";
		config.width = 480;
		config.height = 320;
		
		Board brd = new Board();
		MainGame game = new MainGame(brd);
		brd.register(game);
		new LwjglApplication(game, config);
	}
}
