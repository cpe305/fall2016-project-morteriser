package com.capsuleclash.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * @author Hristo
 *
 * Still unsure of how to go about holding the data for each unique Figure and Action,
 * maybe an XML reader?
 * 
 * Also implements Singleton pattern.
 */
public class FigureCollection {
	private List<Figure> allUniqueFigures;
	
	private static FigureCollection instance;
	
	private FigureCollection() {
		allUniqueFigures = new ArrayList<Figure>();
	}
	
	public static FigureCollection getInstance() {
		if (instance == null) {
			instance = new FigureCollection();
		}
		return instance;
	}
}
