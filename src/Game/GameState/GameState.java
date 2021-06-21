package Game.GameState;

import java.awt.Graphics2D;

/**
 * The superclass for all gamestates that I will add to the game.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void init();
    public abstract void update();
    public abstract void render(Graphics2D g);
    public abstract void handleInput();
}
