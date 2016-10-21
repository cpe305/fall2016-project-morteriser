package com.capsuleclash.game;

import com.capsuleclash.game.Cell.OverlayState;

import java.awt.*;
import java.util.*;

public class Board {
	public static final int ROWS = 10;
	public static final int COLUMNS = 10;
	// probably need to make private
	public Cell[][] board;
	
	public Board() {
		board = new Cell[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = new Cell();
			}
		}
	}


	/**
	 * @param validSteps:
	 *                 an array of legal steps a player can make at any point
	 *                 validSteps for a player that can only move diagonally every step
	 *                 ArrayList[Step(1,1), Step(1,-1), Step(-1,-1), Step(-1,1)]
	 *
	 *
	 *                 move like a knight in chess
	 *                 ArrayList[Step(1,2), Step(-1,2), ... ]
	 *
	 * @param steps:
	 *            number of steps the unit at the start point can make
     *
	 * @param pickOne:
     *               pretty neet feature
	 */
	public boolean [][] getValidMoves(Point startPoint, ArrayList<Step> validSteps, int steps, boolean pickOne) {
        boolean[][] moveMap = new boolean[ROWS][COLUMNS];
		moveMap[startPoint.y][startPoint.x] = true;

		Queue<Point> pointList = new LinkedList<Point>();
        pointList.add(startPoint);

		for (int stepIdx = 0; stepIdx < steps; stepIdx++) {
            int queueSize = pointList.size();
            for (int i = 0; i < queueSize; i++) {

				Point currentPoint = pointList.remove();
				for (Step move : validSteps) {
                    Point proposedMove = new Point(currentPoint.x + move.dx, currentPoint.y + move.dy);

					// check if there is an enemy on this space, you should probably refactor this into a method call (to check for checkmate or whatevs)
                    if (/* !isEnemy(propsedMove) && */
                    	inBounds(proposedMove) &&
						!moveMap[proposedMove.y][proposedMove.x]) {

                        if (pickOne && stepIdx > 0) {
							if (inBounds(new Point(currentPoint.x-move.dx, currentPoint.y-move.dy)) &&
									moveMap[currentPoint.y - move.dy][currentPoint.x - move.dx]) {
								moveMap[proposedMove.y][proposedMove.x] = true;
								pointList.add(proposedMove);
							}

						} else {
							moveMap[proposedMove.y][proposedMove.x] = true;
							pointList.add(proposedMove);
						}
					}
				}
			}
		}

		return moveMap;
	}
	public boolean inBounds(Point p) {
		return p.x >=0 &&
				p.y >= 0 &&
				p.x < COLUMNS &&
				p.y < ROWS;

	}

	// for testing purposes, console version
	public void printBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				//
				// use something like this to change each cell based on their path 
				// board[i][j].setOverlay(OverlayState.MOVE)
				//
				System.out.print("[" + board[i][j].getOverlay() + "]");
			}
			System.out.println();
		}
	}
}
