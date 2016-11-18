package com.capsuleclash.game;

import com.capsuleclash.game.Observer;
import com.capsuleclash.game.Cell.OverlayState;

import java.awt.*;
import java.util.*;

public class Board extends Subject {
	
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	public static final int MAX_TURNS = 20;
	
	public enum TurnState { 
		PLAYER1, PLAYER2
	};

	// probably need to make private
	public Cell[][] board;
	
	private ArrayList<Observer> observers;
	private TurnState turn;
	private int curTurn;
	private Cell old;
	private TurnState compareTurn;
	private Cell.State compareCell;
	private boolean moveable;

	public Board() {
		board = new Cell[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = new Cell();
			}
		}
		turn = TurnState.PLAYER1;
		curTurn = 1;
		observers = new ArrayList<Observer>();
		board[3][0].placeUnit(Cell.State.UNITP1);
		board[4][1].placeUnit(Cell.State.UNITP1);
		board[6][6].placeUnit(Cell.State.UNITP2);
		moveable = true;
		old = board[0][0];
		updateTurn();
	}
	
	public void updateTurn() {
		if (curTurn % 2 > 0) {
			compareCell = Cell.State.UNITP1;
		}
		else {
			compareCell = Cell.State.UNITP2;
		}
	}
	
	public void checkCell(int gridX, int gridY) {
		//System.out.println(gridX + " " + gridY);

		if (board[gridX - 1][gridY].getOverlay() == OverlayState.MOVE &&
				old.getState() == compareCell) {
			board[gridX - 1][gridY].placeUnit(compareCell);
			old.removeUnit();
			old = board[gridX - 1][gridY];
			getValidMoves(new Point(gridX - 1, gridY), 
					new Moves(1).getList(), 1, true, OverlayState.ATTACK);
			moveable = false;
		}
		else if (moveable && board[gridX - 1][gridY].getState() == compareCell) {
			getValidMoves(new Point(gridX - 1, gridY), 
					new Moves(2).getList(), 1, true, OverlayState.MOVE);
			old = board[gridX - 1][gridY];
		}
		else if (!moveable) {
			//if (board[gridX - 1][gridY].getOverlay() == OverlayState.ATTACK)
			eraseOverlay();
			notifyObservers();
			curTurn++;
			moveable = true;
		}

	}

	/**
	 * @param validSteps:
	 *            an array of legal steps a player can make at any point
	 *            validSteps for a player that can only move diagonally every
	 *            step ArrayList[Step(1,1), Step(1,-1), Step(-1,-1), Step(-1,1)]
	 *
	 *
	 *            move like a knight in chess ArrayList[Step(1,2), Step(-1,2),
	 *            ... ]
	 *
	 * @param steps:
	 *            number of steps the unit at the start point can make
	 *
	 * @param pickOne:
	 *            pretty neet feature
	 */
	public boolean[][] getValidMoves(Point startPoint, ArrayList<Step> validSteps, 
			int steps, boolean pickOne, OverlayState type) {
		eraseOverlay();
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

					if (inBounds(proposedMove) &&
					isEmpty(proposedMove, type) && !moveMap[proposedMove.y][proposedMove.x]) {

						if (pickOne && stepIdx > 0) {
							if (inBounds(new Point(currentPoint.x - move.dx, currentPoint.y - move.dy))
									&& moveMap[currentPoint.y - move.dy][currentPoint.x - move.dx]) {
								moveMap[proposedMove.y][proposedMove.x] = true;
								board[proposedMove.x][proposedMove.y].setOverlay(type);
								pointList.add(proposedMove);
							}

						} else {
							moveMap[proposedMove.y][proposedMove.x] = true;
							board[proposedMove.x][proposedMove.y].setOverlay(type);
							pointList.add(proposedMove);
						}
					}
				}
			}
		}
		notifyObservers();
		return moveMap;
	}

	public boolean inBounds(Point p) {
		return p.x >= 0 && p.y >= 0 && p.x < COLUMNS && p.y < ROWS;
	}
	
	public boolean isEmpty(Point p, OverlayState type) {
		Cell.State temp = board[p.x][p.y].getState();
		if (type == OverlayState.ATTACK) {
			return temp != Cell.State.EMPTY && temp != compareCell;
		}
		return temp == Cell.State.EMPTY;
	}

	// for testing purposes, console version
	public void printBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				//
				// use something like this to change each cell based on their
				// path
				// board[i][j].setOverlay(OverlayState.MOVE)
				//
				System.out.print("[" + board[i][j].getOverlay() + "]");
			}
			System.out.println();
		}
	}

	public void eraseOverlay() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j].setOverlay(OverlayState.NONE);
			}
		}
	}
	
	public void register(Observer o) {
		observers.add(o);
	}

	public void unregister(Observer o) {
		observers.remove(o);
	}

	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}
}
