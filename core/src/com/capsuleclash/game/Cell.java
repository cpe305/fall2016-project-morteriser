package com.capsuleclash.game;

/**
 * The cell of a certain tile on the grid that contains info on what state it is in and 
 * what figure is on it, along with which player it belongs to.
 * @author Hristo
 */
public class Cell {
  /**
   * State of the cell based on the unit.
   * EMPTY: no unit on this cell.
   * UNITP1: a unit owned by player 1 is on this cell.
   * UNITP2: a unit owned by player 2 is on this cell.
   * @author Hristo
   */
  public enum State {
    EMPTY, UNITP1, UNITP2
  }
  /**
   * State of the cell's overlay to display options.
   * NONE: cell has no overlay and uses a normal tile.
   * MOVE: tile is now blue to display where to move.
   * ATTACK: tile is now red to display where to attack.
   * @author Hristo
   */
  public enum OverlayState {
    NONE, MOVE, ATTACK
  }

  private State state;
  private OverlayState overlay;
  private Figure figure;

  /**
   * Create a cell that is empty.
   */
  public Cell() {
    state = State.EMPTY;
    overlay = OverlayState.NONE;
    figure = null;
  }
  
  /**
   * Get the current state.
   * @return the current state.
   */
  public State getState() {
	  return state;
  }

  /**
   * Places a unit on this specific cell and updates the state accordingly.
   * @param figure the unit to add.
   * @param state whether the unit is player 1's or player 2's.
   */
  public void placeUnit(Figure figure, State state) {
	this.figure = figure;
    this.state = state;
  }

  /**
   * Removes any unit currently on this cell.
   */
  public void removeUnit() {
	figure = null;
    state = State.EMPTY;
  }

  /**
   * Sets the OverlayState on this cell.
   * @param overlay the state to change to.
   */
  public void setOverlay(OverlayState overlay) {
    this.overlay = overlay;
  }
  
  /**
   * Gets the figure on this cell.
   * @return the figure.
   */
  public Figure getFigure() {
	  return figure;
  }

  /**
   * Gets the OverlayState on this cell.
   * @return the current OverlayState.
   */
  public OverlayState getOverlay() {
	return overlay;
  }
}
