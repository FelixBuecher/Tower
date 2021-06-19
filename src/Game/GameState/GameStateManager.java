package Game.GameState;

import Game.Audio.JukeBox;
import Game.Tools.Constants;

import java.awt.*;

public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;

    private PauseState pauseState;
    private boolean paused;

    public static final int NUMGAMESTATES = 10;
    public static final int MENUSTATE = 1;
    public static final int LEVEL1STATE = 2;
    public static final int LEVEL2STATE = 3;

    public GameStateManager() {
        JukeBox.init();
        gameStates = new GameState[NUMGAMESTATES];
        pauseState = new PauseState(this);
        paused = false;
        currentState = MENUSTATE;
        loadState(currentState);
    }

    private void loadState(int state) {
        if(state == MENUSTATE)
            gameStates[state] = new MenuState(this);
        else if(state == LEVEL1STATE)
            gameStates[state] = new Level1State(this);
        //else if(state == LEVEL2STATE)
        //    gameStates[state] = new Level2State(this);
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

    public void update() {
        if(paused) {
            pauseState.update();
            return;
        }
        if(gameStates[currentState] != null) {
            gameStates[currentState].update();
        }
    }

    public void render(Graphics2D g) {
        if(paused) {
            pauseState.render(g);
            return;
        }
        if(gameStates[currentState] != null) gameStates[currentState].render(g);
        else {
            g.setColor(java.awt.Color.BLACK);
            g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        }
    }
}
