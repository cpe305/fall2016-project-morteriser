package com.capsuleclash.game;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
/**
 * 
 * @author Hristo Stoytchev
 *
 * Holds all the textures to use in the graphical version of the game.
 * Implements a Singleton pattern.
 */
public class Renderer {
	private List<Texture> textures;
	
	private static Renderer instance;
	
	private Renderer() {
		textures = new ArrayList<Texture>();
	}
	
	public static Renderer getInstance() {
		if (instance == null) {
			instance = new Renderer();
		}
		return instance;
	}
}
