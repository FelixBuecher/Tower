package game.input;

import game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all keyboard inputs.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class KeyHandler implements KeyListener{

    public static List<Key> keys = new ArrayList<>();

    public class Key {
        public int presses, absorbs;
        public boolean down, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if(pressed != down) {
                down = pressed;
            }
            if(pressed) {
                presses++;
            }
        }

        public void tick() {
            if(absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key space = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();
    public Key shift = new Key();
    public Key f1 = new Key();

    public KeyHandler(Game game) {
        game.addKeyListener(this);
    }

    public void releaseAll() {
        for(int i = 0; i < keys.size(); i++) {
            keys.get(i).down = false;
        }
    }

    public void tick() {
        for(int i = 0; i < keys.size(); i++) {
            keys.get(i).tick();
        }
    }

    public void toggle(KeyEvent e, boolean pressed) {
        if(e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_SPACE) space.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_E) menu.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_F1) f1.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) shift.toggle(pressed);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
