package Game;

import Game.GameState.GameStateManager;
import Game.Input.Key;
import Game.Tools.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

public class Game extends Canvas implements Runnable, KeyListener {

    // Game thread
    private Thread thread;
    private boolean running;

    // Image
    private BufferedImage image;
    private Graphics2D g;

    // Game state manager
    private GameStateManager gsm;

    public Game() {
        setPreferredSize(new Dimension(Constants.WIDTH * Constants.SCALE, Constants.HEIGHT * Constants.SCALE));
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
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        gsm.render(g);

        // Create lines that are showing the center of the screen
//        g.drawLine(Constants.WIDTH / 2, 0, Constants.WIDTH / 2, Constants.HEIGHT);
//        g.drawLine(0, Constants.HEIGHT / 2, Constants.WIDTH, Constants.HEIGHT / 2);

        Graphics g2 = bs.getDrawGraphics();
        g2.drawImage(image, 0, 0, Constants.WIDTH * Constants.SCALE, Constants.HEIGHT * Constants.SCALE, null);
        g2.dispose();
        bs.show();
    }

    private void init() {

        image = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
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
                System.out.println(Constants.TITLE + frames + " FPS " + updates + " UPS");
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

    public static void main(String[] args) {
        JFrame window = new JFrame(Constants.TITLE);
        window.add(new Game());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
