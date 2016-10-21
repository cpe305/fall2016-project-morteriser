package com.capsuleclash.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.capsuleclash.game.Capsule;
import com.capsuleclash.game.MainGame;
import com.capsuleclash.game.Team;
import com.capsuleclash.game.Board;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MainGame(), config);
		
		Board b = new Board();
		b.printBoard();
		
		/*Team t = new Team();
		t.add(new Capsule(5, "a"));
		t.add(new Capsule(1, "b"));
	    t.add(new Capsule(3, "b"));
		t.print();
		System.out.println("Sorted: ");
		t.sort();
		t.print();*/
	}
}
