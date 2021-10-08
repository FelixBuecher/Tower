package game.gamestate;

import game.audio.JukeBox;
import game.input.KeyHandler;
import game.input.MouseHandler;
import game.util.Constants;

import java.awt.*;

/**
 * The gamestate manager, or state machine so to speak
 * with this class I make sure that I am only in 1 level or screen at a time.
 * It also helps me to make transitions between different game states easier.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class GameStateManager {

    private final GameState[] gameStates;
    private int currentState;

    private final Pause pauseState;
    private boolean paused;

    public static final int NUMGAMESTATES = 10;
    public static final int MENUSTATE = 1;
    public static final int LEVEL1STATE = 2;
    public static final int LEVEL2STATE = 3;

    public GameStateManager() {
        JukeBox.init();
        gameStates = new GameState[NUMGAMESTATES];
        pauseState = new Pause(this);
        paused = false;
        currentState = MENUSTATE;
        loadState(currentState);
    }

    private void loadState(int state) {
        if(state == MENUSTATE)
            gameStates[state] = new Menu(this);
        else if(state == LEVEL1STATE)
            gameStates[state] = new Level1(this);
        else if(state == LEVEL2STATE)
            gameStates[state] = new Level2(this);

//        Game.keyListener = false;
    }

    private void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    public void pause() {
        paused = true;
    }

    public void unPause() {
        paused = false;
    }

    /**
     * Used to update the current gamestate
     */
    public void update() {
        if(paused) {
            pauseState.update();
            return;
        }
        if(gameStates[currentState] != null) {
            gameStates[currentState].update();
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if(paused) {
            pauseState.input(mouse, key);
            return;
        }
        for(int i = 0; i < gameStates.length; i++) {
            if(gameStates[i] != null) {
                gameStates[i].input(mouse, key);
            }
        }
    }

    /**
     * Used to render the current gamestate
     * @param g Graphics2D
     */
    public void render(Graphics2D g) {
        if(paused) {
            pauseState.render(g);
            return;
        }
        if(gameStates[currentState] != null) gameStates[currentState].render(g);
        else {
            g.setColor(java.awt.Color.BLACK);
            g.fillRect(0, 0, Constants.width, Constants.height);
        }
    }
}
