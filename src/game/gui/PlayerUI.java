package game.gui;

import game.entity.mob.player.Player;
import game.util.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

import static game.util.Util.loadImage;

/**
 * Graphical user interface class, this calss will be used
 * to display various information about the character to the player.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class PlayerUI {

    private final Player player;

    private BufferedImage heart;
    private static int FPS;
    /*
     * Maybe I should make this more flexible so I can dynamically add GUI
     * elements based on level (if you are underwater something like air
     * that you normally don't care about for example)
     */

    /**
     * Create the GUI for the player.
     * @param p player
     */
    public PlayerUI(Player p) {
        player = p;
        FPS = 0;
        try {
            // Read in the image for the attribute
            BufferedImage image = loadImage("character32");
            heart = image.getSubimage(0, 0, 32, 32);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFPS(int i) {
        FPS = i;
    }

    /**
     * Used to render the GUI.
     * @param g Graphics2D
     */
    public void render(Graphics2D g) {
        // An example to draw something based on the player attributes
        g.setFont(new Font("Comfortaa", Font.PLAIN, 10));
        g.setColor(Color.decode("0x948668"));
        g.fillRect(0, 0, 250, 50);
        if(player.getHealth() > 0) {
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(10));
            g.drawLine(42, 42, (int) (42 + 188 * (player.getHealth() / player.getMaxHealth())), 42);
        }
        g.drawImage(heart, 0, 10, null);
        g.setColor(java.awt.Color.WHITE);
        g.drawString((int) player.getHealth() + " / " + (int) player.getMaxHealth(), 120, 46);
        g.setColor(java.awt.Color.WHITE);
        g.drawString("FPS: " + FPS, (float) (Constants.width * 0.85), 20);

    }

}
