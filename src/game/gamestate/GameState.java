package game.gamestate;

import game.input.KeyHandler;
import game.input.MouseHandler;
import game.util.Constants;

import java.awt.Graphics2D;

/**
 * The superclass for all gamestates that I will add to the game.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public abstract class GameState {

    protected GameStateManager gsm;

    protected int width = Constants.width;
    protected int height = Constants.height;
    protected int tilesize = Constants.TILESIZE;
    protected int hWidth = width / 2;
    protected int hHeight = height / 2;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void init();
    public abstract void update();
    public abstract void render(Graphics2D g);
    public abstract void input(MouseHandler mouse, KeyHandler key);

}
