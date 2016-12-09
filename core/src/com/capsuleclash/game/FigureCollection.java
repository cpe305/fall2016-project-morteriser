package com.capsuleclash.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

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
		
		try {
			File in = new File("figures.xml");
			ActionFactory af = new ActionFactory();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(in);
	        doc.getDocumentElement().normalize();
	        NodeList nodes = doc.getElementsByTagName("figure");
	        
	        for (int ndx = 0; ndx < nodes.getLength(); ndx++) {
	        	Node node = nodes.item(ndx);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                int id = Integer.parseInt(element.getAttribute("id"));
	                int rarity = Integer.parseInt(
	                		element.getElementsByTagName("rarity").item(0).getTextContent());
	                int size = Integer.parseInt(
	                		element.getElementsByTagName("size").item(0).getTextContent());
	                int health = Integer.parseInt(
	                		element.getElementsByTagName("health").item(0).getTextContent());
	                int attack = Integer.parseInt(
	                		element.getElementsByTagName("attack").item(0).getTextContent());
	                int defense = Integer.parseInt(
	                		element.getElementsByTagName("defense").item(0).getTextContent());
	                int move = Integer.parseInt(
	                		element.getElementsByTagName("move").item(0).getTextContent());
	                String name = element.getElementsByTagName("name").item(0).getTextContent();
	                String type = element.getElementsByTagName("action").item(0).getTextContent();
	                allUniqueFigures.add(new Figure(id, rarity, size, health, attack, 
	                		defense, move, name, af.getAction(type)));
	             }
	        }
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Figure getFigure(int id) {
		Figure uniqueFigure = new Figure(allUniqueFigures.get(id));
		return uniqueFigure;
	}
	
	
	public static FigureCollection getInstance() {
		if (instance == null) {
			instance = new FigureCollection();
		}
		return instance;
	}
}
