package com.capsuleclash.game;
/**
 * Implementation of Factory Method pattern for getting specific actions.
 * 
 * @author Hristo Stoytchev
 *
 */
public class ActionFactory {
	
	public Action getAction(String type) {
		Action action;
		switch (type) {
			case "standard":
				action = new StandardAction();
				break;
			case "pierce":
				action = new PierceAction();
				break;
			default:
				action = new StandardAction();
				break;
		}
		return action;
	}
}
