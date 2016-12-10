package com.capsuleclash.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.capsuleclash.game.Cell.OverlayState;

/**
 * The Main Game based on libgdx's gui and controller. 
 * Handles input and displaying of graphics, relies on the Board for logic.
 * @author Hristo
 */
public class MainGame extends ApplicationAdapter implements Observer {
	
	public static final int BOARD_SIZE = 8;
	public static final int TILE_SIZE = 32;
	public static final int OFFSET = 32;
	public static final int TEAM_SIZE = 4;
	
	public static final int Y_OFFSET = 5;
	public static final int FIRST_UNIT = 3;
	public static final int SECOND_UNIT = 5;
	public static final int THIRD_UNIT = 7;
	public static final int FOURTH_UNIT = 9;
	
	/**
	 * The current screen to be displayed.
	 * PLAY1: Player 1 chooses units.
	 * PLAY2: Player 2 chooses units.
	 * GAME: Player 1 and 2 alternate to play the game.
	 * END1: Player 1 loses the game.
	 * END2: Player 2 loses the game.
	 * @author Hristo
	 */
	public enum MenuState { 
		PLAY1, PLAY2, GAME, END1, END2
	}
	
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
	
	/**
	 * Initialize the MainGame and keep a reference to the Board that is observed.
	 * @param sub the obversed board.
	 */
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
	        		selectUnit((int)gridX, (int)gridY);
	        	}
	        }
		}
	}
	
	/**
	 * See if you selected a valid unit.
	 * @param gridX X coordinate.
	 * @param gridY Y coordinate.
	 */
	public void selectUnit(int gridX, int gridY) {
		if (gridY == Y_OFFSET) {
    		if (gridX == FIRST_UNIT) {
    			addUnit(0);
    		}
    		else if (gridX == SECOND_UNIT) {
    			addUnit(1);
    		}
    		else if (gridX == THIRD_UNIT) {
    			addUnit(2);
    		}
    		else if (gridX == FOURTH_UNIT) {
    			addUnit(3);
    		}
		}
	}
	
	/**
	 * Adds a unit to the board based on when it was selected.
	 * @param index the id of the unit being added.
	 */
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
	
	/**
	 * When the user presses on a location in the grid, it calls the board to
	 * check the cell for any changes.
	 * @param gridX the X coordinate of the cell clicked on.
	 * @param gridY the Y coordinate of the cell clicked on.
	 */
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

	/**
	 * Loads the display for the selecting unit and won game screens.
	 */
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

		OverlayState overState;
		Cell.State state;
		Texture toUse;
		batch.begin();
		
		if (sub.getTurn() % 2 == 1) {
			batch.draw(play1, 300, 260);
		}
		else {
			batch.draw(play2, 300, 260);
		}
		
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				overState = sub.getBoard()[row][col].getOverlay();
				toUse = renderer.getTexture(4);
				if (overState == OverlayState.MOVE) {
					toUse = renderer.getTexture(5);
				} 
				else if (overState == OverlayState.ATTACK) {
					toUse = renderer.getTexture(6);
				}
				batch.draw(toUse, (float) row * TILE_SIZE + OFFSET, 
						(float) col * TILE_SIZE + OFFSET);
				
				state = sub.getBoard()[row][col].getState();
				if (state != Cell.State.EMPTY) {
					toUse = renderer.getTexture(sub.getBoard()[row][col].getFigure().getIdentity());
					batch.draw(toUse, (float) row * TILE_SIZE + OFFSET, 
							(float) col * TILE_SIZE + OFFSET);
				}
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
