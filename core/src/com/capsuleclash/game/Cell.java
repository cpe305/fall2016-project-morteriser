package com.capsuleclash.game;

public class Cell {
	public enum State {EMPTY, UNITP1, UNITP2};
	public enum OverlayState {NONE, MOVE, ATTACK};
	
	private State state;
	private OverlayState overlay;
	
	// keep data for the current unit on this cell
	
	public Cell() {
		state = State.EMPTY;
		overlay = OverlayState.NONE;
	}
	
	// adjust to take in a unit and the current turn state
	public void placeUnit() {
		state = State.UNITP1;
	}
	
	public void removeUnit() {
		state = State.EMPTY;
	}
	
	public void setOverlay(OverlayState overlay) {
		this.overlay = overlay;
	}
	
	// for testing purposes, will replace with textures
	public String getOverlay() {
		String result = "N";
		switch(overlay) {
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
		return result;
	}
}
