package Game.GUI;

import Game.Entity.Mob.Player.Player;
import Game.Tools.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI {
    private Player player;

    private BufferedImage heart;

    public GUI(Player p) {
        player = p;
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Textures/Sprites/character.png"));
            heart = image.getSubimage(0, 0, 13, 12);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D g) {
        for(int i = 0; i < player.getHealth(); i++) {
            g.drawImage(heart, 10 + i * 15, 10, null);
        }
        g.setColor(java.awt.Color.WHITE);
        g.drawString(player.getTimeToString(), (float) (Constants.WIDTH * 0.9), 20);
    }

}
