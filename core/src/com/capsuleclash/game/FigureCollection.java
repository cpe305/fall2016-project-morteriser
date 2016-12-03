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
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(in);
	        doc.getDocumentElement().normalize();
	        NodeList nodes = doc.getElementsByTagName("figure");
	        
	        for (int ndx = 0; ndx < nodes.getLength(); ndx++) {
	        	Node node = nodes.item(ndx);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                String id = element.getAttribute("id");
	                String rarity = element.getElementsByTagName("rarity").item(0).getTextContent();
	                String size = element.getElementsByTagName("size").item(0).getTextContent();
	                String health = element.getElementsByTagName("health").item(0).getTextContent();
	                String attack = element.getElementsByTagName("attack").item(0).getTextContent();
	                String defense = element.getElementsByTagName("defense").item(0).getTextContent();
	                String move = element.getElementsByTagName("move").item(0).getTextContent();
	                String name = element.getElementsByTagName("name").item(0).getTextContent();
	             }
	        }
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public static FigureCollection getInstance() {
		if (instance == null) {
			instance = new FigureCollection();
		}
		return instance;
	}
}
