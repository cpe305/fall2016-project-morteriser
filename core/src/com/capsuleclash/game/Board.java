package com.capsuleclash.game;

import com.capsuleclash.game.Cell.OverlayState;

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
