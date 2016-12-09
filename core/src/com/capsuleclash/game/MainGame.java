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
	public static final int TEAM_SIZE = 4;
	
	public enum MenuState { 
		PLAY1, PLAY2, GAME, END1, END2
	};
	
	private SpriteBatch batch;
	private Renderer renderer;
	private FigureCollection fc;
	private Board sub;
	private Vector3 position;
	private MenuState menu;
	private int count;
	
	private Texture menu1;
	private Texture menu2;
	private Texture play1;
	private Texture play2;
	private Texture win;
	//private Texture tile;
	//private Texture move;
	//private Texture atk;
	//private Texture fig;
	//private Texture fig2;
	
	public MainGame(Subject sub) {
		if (sub instanceof Board) {
			this.sub = (Board)sub;
		}
        position = new Vector3();
        menu = MenuState.PLAY1;
        fc = FigureCollection.getInstance();
        count = 0;
	}
	
	
	@Override
	public void create () {
        batch = new SpriteBatch();
		renderer = Renderer.getInstance();

		menu1 = new Texture("screen1.png");
		menu2 = new Texture("screen2.png");
		play1 = new Texture("player1.png");
		play2 = new Texture("player2.png");
		win = new Texture("win.png");
        //tile = new Texture("tile.png");
		//move = new Texture("movetile.png");
		//atk = new Texture("attacktile.png");
		//fig = new Texture("testfig.png");
		//fig2 = new Texture("testfig2.png");
		//update();
		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (menu == MenuState.GAME) {
			update();
		}
		else {
			loadMenu();
		}
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();

		
		if (Gdx.input.justTouched()) {
	        position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	        
	        double gridX = Math.floor(position.x / TILE_SIZE);
	        double gridY = Math.floor(position.y / TILE_SIZE);
	        
	        if (gridX > 0 && gridY > 0) {
	        	if (menu == MenuState.GAME) {
	        		press(gridX, gridY);
	        	}
	        	else if (menu == MenuState.END1 || menu == MenuState.END2) {
	        		menu = MenuState.PLAY1;
	        		sub.reset();
	        		count = 0;
	        	}
	        	else {
	        		if (gridX == 3 && gridY == 5) {
	        			addUnit(0);
	        		}
	        		else if (gridX == 5 && gridY == 5) {
	        			addUnit(1);
	        		}
	        		else if (gridX == 7 && gridY == 5) {
	        			addUnit(2);
	        		}
	        		else if (gridX == 9 && gridY == 5) {
	        			addUnit(3);
	        		}
	        	}
	        }
		}
	}
	
	public void addUnit(int index) {
		sub.addUnit(fc.getFigure(index), menu == MenuState.PLAY1, count);
		count++;
		
		if (count == TEAM_SIZE) {
			menu = MenuState.PLAY2;
		}
		if (count >= TEAM_SIZE * 2) {
			menu = MenuState.GAME;
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
	}

	public void loadMenu() {
		batch.begin();
		
		if (menu == MenuState.PLAY1) {
			batch.draw(menu1, 0, 0);
		}
		else if (menu == MenuState.PLAY2) {
			batch.draw(menu2, 0, 0);
		}
		else if (menu == MenuState.END1) {
			batch.draw(play2, 160, 160);
			batch.draw(win, 175, 100);
		}
		else {
			batch.draw(play1, 160, 160);
			batch.draw(win, 175, 100);
		}
		
		batch.end();
	}
	
	@Override
	public void update() {

		OverlayState overState = OverlayState.NONE;
		Cell.State state = Cell.State.EMPTY;
		Texture toUse = renderer.getTexture(4);
		batch.begin();
		
		if (sub.getTurn() % 2 == 1) {
			batch.draw(play1, 300, 260);
		}
		else {
			batch.draw(play2, 300, 260);
		}
		
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				overState = sub.board[row][col].getOverlay();
				toUse = renderer.getTexture(4);
				if (overState == OverlayState.MOVE) {
					toUse = renderer.getTexture(5);
				} 
				else if (overState == OverlayState.ATTACK) {
					toUse = renderer.getTexture(6);
				}
				batch.draw(toUse, row * TILE_SIZE + OFFSET, col * TILE_SIZE + OFFSET);
				
				state = sub.board[row][col].getState();
				if (state != Cell.State.EMPTY) {
					toUse = renderer.getTexture(sub.board[row][col].getFigure().getIdentity());
					batch.draw(toUse, row * TILE_SIZE + OFFSET, col * TILE_SIZE + OFFSET);
				}
				//System.out.println(row + " " + col);
				//sub.board[row][col].setOverlay(OverlayState.NONE);
			}
		}
		
		if (sub.getTeams().get(0).totalHealth() == 0) {
			menu = MenuState.END1;
		}
		if (sub.getTeams().get(1).totalHealth() == 0) {
			menu = MenuState.END2;
		}
		batch.end();
	}
}
