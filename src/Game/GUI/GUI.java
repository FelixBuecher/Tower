package Game.GUI;

import Game.Entity.Mob.Player.Player;
import Game.Tools.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Graphical user interface class, this calss will be used
 * to display various information about the character to the player.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class GUI {

    private final Player player;

    private BufferedImage heart;

    /*
     * Maybe I should make this more flexible to I can dynamically add GUI
     * elements based on level (if you are underwater something like air
     * that you normally don't care about for example)
     */

    /**
     * Create the GUI for the player.
     * @param p player
     */
    public GUI(Player p) {
        player = p;
        try {
            // Read in the image for the attribute
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Textures/Sprites/character.png")));
            heart = image.getSubimage(0, 0, 13, 12);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to render the GUI.
     * @param g Graphics2D
     */
    public void render(Graphics2D g) {
        // An example to draw something based on the player attributes
        for(int i = 0; i < player.getHealth(); i++) {
            g.drawImage(heart, 10 + i * 15, 10, null);
        }
        g.setColor(java.awt.Color.WHITE);
        g.drawString(player.getTimeToString(), (float) (Constants.WIDTH * 0.9), 20);
    }

}
