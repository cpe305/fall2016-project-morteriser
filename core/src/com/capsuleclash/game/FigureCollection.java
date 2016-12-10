package com.capsuleclash.game;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
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
	
	private static final Logger logger = Logger.getLogger(FigureCollection.class.getName());
	
	private FigureCollection() {
		allUniqueFigures = new ArrayList<>();
		
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
	                allUniqueFigures.add(new Figure(id, health, attack, 
	                		defense, move, name, af.getAction(type)));
	             }
	        }
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "XML reading error: ", e);
		}
	}
	
	/**
	 * Gets the figure based on the id and creates a unique instance of it.
	 * @param id of the figure to differentiate them.
	 * @return a new instance of the figure with that id.
	 */
	public Figure getFigure(int id) {
		return new Figure(allUniqueFigures.get(id));
	}
	
	
	public static FigureCollection getInstance() {
		if (instance == null) {
			instance = new FigureCollection();
		}
		return instance;
	}
}
