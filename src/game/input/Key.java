package game.input;

import java.awt.event.KeyEvent;

/**
 * Handler for keyboard input.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Key {

    public static final int NUM_KEYS = 16;

	public static boolean[] key = new boolean[NUM_KEYS];
    public static boolean[] prevKey = new boolean[NUM_KEYS];

    public static int UP = 0;
    public static int LEFT = 1;
    public static int DOWN = 2;
    public static int RIGHT = 3;
    public static int SHIFT = 4;
    public static int SPACE = 5;
    public static int E_KEY = 6;
    public static int ESCAPE = 9;

    public static void keySet(int i, boolean b) {
        if(i == KeyEvent.VK_W || i == KeyEvent.VK_UP) key[UP] = b;
        else if(i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) key[LEFT] = b;
        else if(i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) key[DOWN] = b;
        else if(i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) key[RIGHT] = b;
        else if(i == KeyEvent.VK_SHIFT) key[SHIFT] = b;
        else if(i == KeyEvent.VK_SPACE) key[SPACE] = b;
        else if(i == KeyEvent.VK_E) key[E_KEY] = b;
        else if(i == KeyEvent.VK_ESCAPE) key[ESCAPE] = b;
    }

	public static void update() {
        System.arraycopy(key, 0, prevKey, 0, NUM_KEYS);
//        for(int i = 0; i < NUM_KEYS; i++) {
//            prevKey[i] = key[i];
//        }
	}

    public static boolean isPressed(int i) {
        return key[i] && !prevKey[i];
    }

    public static boolean anyKeyPress() {
        for(int i = 0; i < NUM_KEYS; i++) {
            if(key[i]) return true;
        }
        return false;
    }


}
