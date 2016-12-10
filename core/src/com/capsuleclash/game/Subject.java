package com.capsuleclash.game;

/**
 * Subject that is observed by Observers, based on Observer pattern.
 * @author Hristo
 */
public interface Subject {
  /**
   * Adds Observer to list to notify.
   * @param o Observer to be added.
   */
  public void register(Observer o);
  /**
   * Removes Observer from the list.
   * @param o Observer to be removed.
   */
  public void unregister(Observer o);
  /**
   * Notifies Observers of any changes on this Subject.
   */
  public void notifyObservers(); 
}