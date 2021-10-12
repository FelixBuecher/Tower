package game;

import game.gui.PlayerUI;
import game.gamestate.GameStateManager;
import game.input.KeyHandler;
import game.input.MouseHandler;
import game.util.Constants;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
//import java.awt.RenderingHints;

public final class Game extends Canvas implements Runnable {

    // Game thread
//    private Thread thread;
    private boolean running;
    private Thread thread;

    // Image
    private BufferedImage image;
    private Graphics2D g;

    private MouseHandler mouseHandler;
    private KeyHandler key;

    // Game state manager
    private GameStateManager gsm;

    public Game() {
        setPreferredSize(new Dimension( (int) (Constants.width * Constants.scale),
                (int) (Constants.height * Constants.scale)));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void update() {
        gsm.update();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        gsm.render(g);

        // Create lines that are showing the center of the screen
//        g.drawLine(Constants.WIDTH / 2, 0,
//                Constants.WIDTH / 2, Constants.HEIGHT);
//        g.drawLine(0, Constants.HEIGHT / 2,
//                Constants.WIDTH, Constants.HEIGHT / 2);

        Graphics g2 = bs.getDrawGraphics();
        g2.drawImage(image, 0, 0, (int) (Constants.width * Constants.scale),
                (int) (Constants.height * Constants.scale), null);
        g2.dispose();
        bs.show();
    }

    private void init() {

        image = new BufferedImage(Constants.width,
                Constants.height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) image.getGraphics();

        // Antialiasing for the titlescreen
//        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        running = true;

        mouseHandler = new MouseHandler(this);
        key = new KeyHandler(this);
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
                input(mouseHandler, key);
                updates++;
                delta--;
            }

            input(mouseHandler, key);
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                PlayerUI.setFPS(frames);
                System.out.println(Constants.TITLE + frames
                        + " FPS " + updates + " UPS");
                updates = 0;
                frames = 0;
            }
        }

    }

    public void input(MouseHandler mouseHandler, KeyHandler key) {
        gsm.input(mouseHandler, key);
    }

    public static void main(final String[] args) {
        JFrame window = new JFrame(Constants.TITLE);
        window.add(new Game());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
