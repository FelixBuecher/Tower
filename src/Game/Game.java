package Game;

import javax.swing.*;

public class Game extends JPanel implements Runnable {

    // dimensions
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 3;

    // game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    // image
    private BufferedImage image;
    private Graphics2D g;

    // game state manager
    private GameStateManager gsm;


    @Override
    public void run() {

    }
}
