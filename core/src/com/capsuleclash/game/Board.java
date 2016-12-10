package com.capsuleclash.game;

import com.capsuleclash.game.Observer;
import com.capsuleclash.game.Cell.OverlayState;

import java.util.List;
import java.awt.*;
import java.util.*;
/**
 * The Board that contains all of the cells in the game to create a tiled grid that
 * figures can move on. Checks for legality of input based on the grid and updates 
 * any changes that occur, notifying the observers when something new is to be shown.
 * @author Hristo
 *
 */
public class Board implements Subject {
	
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	public static final int MAX_TURNS = 20;
	

	// probably need to make private
	private Cell[][] grid;
	
	private ArrayList<Observer> observers;
	private int curTurn;
	private Cell old;
	private Cell.State compareCell;
	private boolean moveable;
	private ArrayList<Team> team;

	/**
	 * Instantiates a new grid, teams for each player, and sets current turn variables.
	 */
	public Board() {
		grid = new Cell[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				grid[i][j] = new Cell();
			}
		}
		curTurn = 1;
		observers = new ArrayList<>();
		team = new ArrayList<>();
		
		// player 1
		team.add(new Team());
		// player 2
		team.add(new Team());
		
		moveable = true;
		old = grid[0][0];
		updateTurn();
	}
	
	/**
	 * For when a game is complete, reset the grid so that it doesn't use the previous game.
	 */
	public void reset() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				grid[i][j] = new Cell();
			}
		}
		curTurn = 1;
		team = new ArrayList<>();
		
		// player 1
		team.add(new Team());
		// player 2
		team.add(new Team());
		
		moveable = true;
		old = grid[0][0];
		updateTurn();
	}
	
	/**
	 * Adds a Figure based on whether or not it is player 1's.
	 * @param figure the unit to add.
	 * @param player1 an input of (menu == MenuState.PLAY1) to see if it is player 1's choice.
	 * @param count the current amount of times this method has been called.
	 */
	public void addUnit(Figure figure, boolean player1, int count) {
		if (player1) {
			team.get(0).add(figure);
			grid[2 + count][0].placeUnit(figure, Cell.State.UNITP1);
		}
		else {
			team.get(1).add(figure);
			grid[2 + count % 4][7].placeUnit(figure, Cell.State.UNITP2);
		}
	}
	/**
	 * Changes the state of what to compare Cells with to ensure player order is tracked.
	 */
	public void updateTurn() {
		if (curTurn % 2 > 0) {
			compareCell = Cell.State.UNITP1;
		}
		else {
			compareCell = Cell.State.UNITP2;
		}
	}
	
	/**
	 * Check the legality of options based on a cell that has been clicked.
	 * @param gridX the X coordinate on the grid.
	 * @param gridY the Y coordinate on the grid.
	 */
	public void checkCell(int gridX, int gridY) {

		if (grid[gridX - 1][gridY].getOverlay() == OverlayState.MOVE &&
				old.getState() == compareCell) {
			// a legality check for the ability to attack after you have moved to a spot
			Figure fig = old.getFigure();
			old.removeUnit();
			grid[gridX - 1][gridY].placeUnit(fig, compareCell);
			old = grid[gridX - 1][gridY];
			getValidMoves(new Point(gridX - 1, gridY), 
					new Moves(1).getList(), 1, true, OverlayState.ATTACK);
			moveable = false;
		}
		else if (moveable && grid[gridX - 1][gridY].getState() == compareCell) {
			// a legality check for the ability to move after you have clicked on your unit
			old = grid[gridX - 1][gridY];
			getValidMoves(new Point(gridX - 1, gridY), 
					new Moves(old.getFigure().getMove()).getList(), 
					1, true, OverlayState.MOVE);
			grid[gridX - 1][gridY].setOverlay(OverlayState.MOVE);
		}
		else if (!moveable) {
			// a legality check for the end of the turn
			if (grid[gridX - 1][gridY].getOverlay() == OverlayState.ATTACK) {
				// deal damage when the last action was on an attackable square
				old.getFigure().getAction().calculate(old.getFigure(), 
						grid[gridX - 1][gridY].getFigure());
				if (grid[gridX - 1][gridY].getFigure().getHealth() == 0) {
					grid[gridX - 1][gridY].removeUnit();
				}
			}
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
	 * @param startPoint: 
	 * 			  the origin point of what is going to be checked
	 * @param type:
	 *            the intended OverlayState to change to move or attack.
	 * @return an array of boolean values that have been generated.
	 */
	public boolean[][] getValidMoves(Point startPoint, List<Step> validSteps, 
			int steps, boolean pickOne, OverlayState type) {
		eraseOverlay();
		boolean[][] moveMap = new boolean[ROWS][COLUMNS];
		moveMap[startPoint.y][startPoint.x] = true;
		Queue<Point> pointList = new LinkedList<>();
		pointList.add(startPoint);

		for (int stepIdx = 0; stepIdx < steps; stepIdx++) {
			int queueSize = pointList.size();
			for (int i = 0; i < queueSize; i++) {

				Point currentPoint = pointList.remove();
				for (Step move : validSteps) {
					Point proposedMove = new Point(currentPoint.x + move.getX(), 
							currentPoint.y + move.getY());

					if (isEmpty(proposedMove, type) && !moveMap[proposedMove.y][proposedMove.x]) {

						if (pickOne && stepIdx > 0) {
							if (inBounds(new Point(currentPoint.x - move.getX(), currentPoint.y - move.getY()))
									&& moveMap[currentPoint.y - move.getY()][currentPoint.x - move.getX()]) {
								moveMap[proposedMove.y][proposedMove.x] = true;
								grid[proposedMove.x][proposedMove.y].setOverlay(type);
								pointList.add(proposedMove);
							}

						} else {
							moveMap[proposedMove.y][proposedMove.x] = true;
							grid[proposedMove.x][proposedMove.y].setOverlay(type);
							pointList.add(proposedMove);
						}
					}
				}
			}
		}
		notifyObservers();
		return moveMap;
	}

	/**
	 * Check if the proposed point is within the dimensions of the grid.
	 * @param p the Point that might be on the grid.
	 * @return whether the Point is on the grid or not.
	 */
	public boolean inBounds(Point p) {
		return p.x >= 0 && p.y >= 0 && p.x < COLUMNS && p.y < ROWS;
	}
	
	/**
	 * Check if the proposed point is empty in regards to the current state.
	 * @param p the Point to check.
	 * @param type if ATTACK then it will only look for filled enemy units.
	 * @return the proposed point being empty, or filled if in ATTACK OverlayState
	 */
	public boolean isEmpty(Point p, OverlayState type) {
		if (!inBounds(p)) {
			return false;
		}
		Cell.State temp = grid[p.x][p.y].getState();
		if (type == OverlayState.ATTACK) {
			return temp != Cell.State.EMPTY && temp != compareCell;
		}
		return temp == Cell.State.EMPTY;
	}

	/**
	 * Revert OverlayStates after a move or attack has been done.
	 */
	public void eraseOverlay() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				grid[i][j].setOverlay(OverlayState.NONE);
			}
		}
	}
	
	/**
	 * Get the teams from player1 and player2 to check their statistics.
	 * @return a list containing both player's teams.
	 */
	public List<Team> getTeams() {
		return team;
	}
	
	/**
	 * Gets the current turn.
	 * @return current turn.
	 */
	public int getTurn() {
		return curTurn;
	}
	
	/**
	 * Gets the current grid.
	 * @return current grid.
	 */
	public Cell[][] getBoard() {
		return grid;
	}
	
	/**
	 * Observer pattern implementation to add Observers.
	 * @param o the Observer to add.
	 */
	@Override
	public void register(Observer o) {
		observers.add(o);
	}

	/**
	 * Observer pattern implementation to remove Observers.
	 * @param o Observer to remove.
	 */
	@Override
	public void unregister(Observer o) {
		observers.remove(o);
	}

	/**
	 * Observer pattern implementation to notify Observers of any changes.
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}
}
