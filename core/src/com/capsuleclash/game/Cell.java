package com.capsuleclash.game;

import java.util.ArrayList;


public class Cell {
  public enum State {
    EMPTY, UNITP1, UNITP2
  };
  public enum OverlayState {
    NONE, MOVE, ATTACK
  };

  private State state;
  private OverlayState overlay;

  // keep data for the current unit on this cell

  public Cell() {
    state = State.EMPTY;
    overlay = OverlayState.NONE;
  }
  
  public State getState() {
	  return state;
  }

  // adjust to take in a unit and the current turn state
  public void placeUnit(State state) {
    this.state = state;
  }

  public void removeUnit() {
    state = State.EMPTY;
  }

  public void setOverlay(OverlayState overlay) {
    this.overlay = overlay;
  }

  public OverlayState getOverlay() {
	return overlay;
    /**String result = "N";
    switch (overlay) {
      case NONE:
        result = "N";
        break;
      case MOVE:
        result = "M";
        break;
      case ATTACK:
        result = "A";
        break;
    }
    return result;*/
  }
}
