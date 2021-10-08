package game.gamestate;

import game.input.KeyHandler;
import game.input.MouseHandler;

import java.awt.*;

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

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Game Paused", width / 3 + font.getSize(), hHeight - font.getSize());
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();
        key.menu.tick();

        if(key.escape.clicked) {
            gsm.unPause();
        }

        if(key.menu.clicked) {
            gsm.unPause();
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }
}
