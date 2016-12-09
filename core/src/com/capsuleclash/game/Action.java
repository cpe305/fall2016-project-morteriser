package com.capsuleclash.game;

/**
 * @author Hristo Stoytchev
 *
 * To implement as Strategy pattern.
 */
public interface Action {

	public int calculate(Figure user, Figure target);
}
