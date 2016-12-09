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
  private Figure figure;

  // keep data for the current unit on this cell

  public Cell() {
    state = State.EMPTY;
    overlay = OverlayState.NONE;
    figure = null;
  }
  
  public State getState() {
	  return state;
  }

  // adjust to take in a unit and the current turn state
  public void placeUnit(Figure figure, State state) {
	this.figure = figure;
    this.state = state;
  }

  public void removeUnit() {
	figure = null;
    state = State.EMPTY;
  }

  public void setOverlay(OverlayState overlay) {
    this.overlay = overlay;
  }
  
  public Figure getFigure() {
	  return figure;
  }

  public OverlayState getOverlay() {
	return overlay;
  }
}
