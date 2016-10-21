package com.capsuleclash.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.capsuleclash.game.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new MainGame(), config);
		
		Board b = new Board();
		//b.printBoard();
		
		/*Team t = new Team();
		t.add(new Capsule(5, "a"));
		t.add(new Capsule(1, "b"));
	    t.add(new Capsule(3, "b"));
		t.print();
		System.out.println("Sorted: ");
		t.sort();
		t.print();*/
		boolean [][] bishop = b.getValidMoves(new Point(1,1), new ArrayList<Step>() {
			{
				add(new Step(1, 1));
				add(new Step(-1, -1));
				add(new Step(1, -1));
				add(new Step(-1, 1));
			}
		}, 100, true);

        for (boolean[] a : bishop) {
            System.out.println(Arrays.toString(a));
		}

		boolean [][] rook = b.getValidMoves(new Point(1,1), new ArrayList<Step>() {
			{
				add(new Step(0, 1));
				add(new Step(1, 0));
				add(new Step(-1, 0));
				add(new Step(0, -1));
			}
		}, 100, true);

		for (boolean[] a : rook) {
			System.out.println(Arrays.toString(a));
		}

		boolean [][] moves2 = b.getValidMoves(new Point(1,1), new ArrayList<Step>() {
			{
				add(new Step(0, 1));
				add(new Step(1, 0));
				add(new Step(-1, 0));
				add(new Step(0, -1));
			}
		}, 3, false);

		for (boolean[] a : moves2) {
			System.out.println(Arrays.toString(a));
		}
	}
}
