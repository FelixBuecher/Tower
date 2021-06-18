package Game.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    private final int w, h;
    private BufferedImage image;
    private Sprite[] sprites;

    public SpriteSheet(String path, int w, int h) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
