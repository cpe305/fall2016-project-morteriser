package com.capsuleclash.game;
/**
 * Implementation of Factory Method pattern for getting specific actions.
 * 
 * @author Hristo Stoytchev
 *
 */
public class ActionFactory {
	
	/**
	 * The factory method, gets the specific action based on the name used in
	 * the xml documents for figurines.
	 * @param type the name of the action.
	 * @return one of many valid actions based on the type.
	 */
	public Action getAction(String type) {
		Action action = new StandardAction();
		switch (type) {
			case "standard":
				action = new StandardAction();
				break;
			case "pierce":
				action = new PierceAction();
				break;
			default:
				break;
		}
		return action;
	}
}
