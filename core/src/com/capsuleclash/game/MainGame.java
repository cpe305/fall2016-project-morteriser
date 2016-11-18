package com.capsuleclash.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.capsuleclash.game.Cell.OverlayState;

public class MainGame extends ApplicationAdapter implements Observer {
	
	public static final int BOARD_SIZE = 8;
	public static final int TILE_SIZE = 32;
	public static final int OFFSET = 32;
	
	private SpriteBatch batch;
	private Renderer renderer;
	private Board sub;
	private Vector3 position;
	
	private Texture tile;
	private Texture move;
	private Texture atk;
	private Texture fig;
	private Texture fig2;
	
	public MainGame(Subject sub) {
		if (sub instanceof Board) {
			this.sub = (Board)sub;
		}
        position = new Vector3();
		renderer = Renderer.getInstance();
	}
	
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        tile = new Texture("tile.png");
		move = new Texture("movetile.png");
		atk = new Texture("attacktile.png");
		fig = new Texture("testfig.png");
		fig2 = new Texture("testfig2.png");
		//update();
		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
		
		if (Gdx.input.justTouched()) {
	        position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	        
	        double gridX = Math.floor(position.x / TILE_SIZE);
	        double gridY = Math.floor(position.y / TILE_SIZE);
	        
	        if (gridX > 0 && gridY > 0) {
	        	press(gridX, gridY);
	        }
		}
	}
	
	public void press(double gridX, double gridY) {
		sub.updateTurn();
		if (gridX < 9 && gridY < 9) {
			sub.checkCell((int)gridX, Math.abs(BOARD_SIZE - (int)gridY));
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		tile.dispose();
	}

	@Override
	public void update() {

		OverlayState overState = OverlayState.NONE;
		Cell.State state = Cell.State.EMPTY;
		Texture toUse = tile;
		batch.begin();
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				overState = sub.board[row][col].getOverlay();
				toUse = tile;
				if (overState == OverlayState.MOVE) {
					toUse = move;
				} 
				else if (overState == OverlayState.ATTACK) {
					toUse = atk;
				}
				batch.draw(toUse, row * TILE_SIZE + OFFSET, col * TILE_SIZE + OFFSET);
				
				state = sub.board[row][col].getState();
				if (state != Cell.State.EMPTY) {
					if (state == Cell.State.UNITP1) {
						toUse = fig;
					}
					else {
						toUse = fig2;
					}
					batch.draw(toUse, row * TILE_SIZE + OFFSET, col * TILE_SIZE + OFFSET);
				}
				//System.out.println(row + " " + col);
				//sub.board[row][col].setOverlay(OverlayState.NONE);
			}
		}
		batch.end();
	}
}
