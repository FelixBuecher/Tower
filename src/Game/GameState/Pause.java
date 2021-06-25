package Game.GameState;

import Game.Input.Key;

import java.awt.*;
import Game.Tools.Constants;

/**
 * Simple pause state, I am not quite sure if I will use this in the game,
 * but I will keep it for now, I might be restructure it to an option state
 * later on in the production. It basically is just a placeholder for when you
 * press esc in the game.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Pause extends GameState {

    private Font font;

    public Pause(GameStateManager gsm) {
        super(gsm);
        font = new Font("Century Gothic", Font.PLAIN, 14);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Game Paused", Constants.WIDTH / 3 + font.getSize(), Constants.HEIGHT / 2 - font.getSize());
    }

    @Override
    public void handleInput() {
        if(Key.isPressed(Key.ESCAPE)) gsm.unPause();
        if(Key.isPressed(Key.E_KEY)) {
            gsm.unPause();
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }
}
