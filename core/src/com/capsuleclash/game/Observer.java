package com.capsuleclash.game;

/**
 * An Observer that observes a Subject, use of Observer pattern.
 * @author Hristo
 */
@FunctionalInterface
public interface Observer {
  /**
   * Called whenever the subject notifies observers.
   */
  public void update();
}
