package com.capsuleclash.game;

/**
 * Created by jackson on 10/20/16.
 */
public class Step {
    public int dx;
    public int dy;
    public Step(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public boolean equals(Step other) {
        return dx == other.dx && dy == other.dy;
    }
}
