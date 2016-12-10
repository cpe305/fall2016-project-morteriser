package com.capsuleclash.game;

/**
 * Created by jackson on 10/20/16.
 */
public class Step {
    private int dx;
    private int dy;
    
    /**
     * A step that has coordinates.
     * @param dx the x coordinate.
     * @param dy the y coordinate.
     */
    public Step(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Check if this Step equals another Step.
     * @param other the step to compare with.
     * @return if their position is equal.
     */
    public boolean equalsStep(Step other) {
    	if (other != null) {
    		return dx == other.getX() && dy == other.getY();
    	}
        return false;
    }
    
    /**
     * Get X coordinate.
     * @return X coordinate.
     */
    public int getX() {
    	return dx;
    }
    
    /**
     * Get Y coordinate.
     * @return Y coordinate.
     */
    public int getY() {
    	return dy;
    }
}
