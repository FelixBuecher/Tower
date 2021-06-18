package Game;

import Game.GameState.GameStateManager;
import Game.Input.Key;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    // Dimensions
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 2;

    // Game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    // Image
    private BufferedImage image;
    private Graphics2D g;

    // Game state manager
    private GameStateManager gsm;

    // Other
    private static String title = "Tower";

    public Game() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void update() {
        gsm.update();
        Key.update();
    }

    private void render() {
        gsm.render(g);
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g2 = bs.getDrawGraphics();// getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();
        bs.show();
    }

    private void init() {

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        // Antialiasing for the titlescreen
        //g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        running = true;
        gsm = new GameStateManager();
    }

    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) * (0.00000006);
            lastTime = currentTime;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(title + frames + " FPS " + updates + " UPS");
                updates = 0;
                frames = 0;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Key.keySet(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Key.keySet(e.getKeyCode(), false);
    }

//    public static void main(String[] args) {
//        JFrame window = new JFrame("Artifact");
//        window.add(new Game());
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setResizable(false);
//        window.pack();
//        window.setLocationRelativeTo(null);
//        window.setVisible(true);
//    }
}
