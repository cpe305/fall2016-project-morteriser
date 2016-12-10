package com.capsuleclash.game;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;

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
 * @author Hristo Stoytchev
 *
 * Holds all the textures to use in the graphical version of the game.
 * Implements a Singleton pattern.
 */
public class Renderer {
	private List<Texture> textures;
	
	private static Renderer instance;
	
	private static final Logger logger = Logger.getLogger(FigureCollection.class.getName());

	
	private Renderer() {
		textures = new ArrayList<>();

		try {
			File in = new File("textures.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(in);
	        doc.getDocumentElement().normalize();
	        NodeList nodes = doc.getElementsByTagName("texture");
	        
	        for (int ndx = 0; ndx < nodes.getLength(); ndx++) {
	        	Node node = nodes.item(ndx);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                String text = element.getElementsByTagName("file").item(0).getTextContent();
	                textures.add(new Texture(text));
	             }
	        }
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "Texture loading error: ", e);
		}
	}
	
	/**
	 * Gets the Texture based on its unique id.
	 * @param id the Texture's unique id.
	 * @return the Texture to be displayed.
	 */
	public Texture getTexture(int id) {
		return textures.get(id);
	}
	
	public static Renderer getInstance() {
		if (instance == null) {
			instance = new Renderer();
		}
		return instance;
	}
}
