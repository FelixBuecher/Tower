package game.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * I will create some general static methods here that I can use to make things easier for me
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Util {

    /**
     * Creates a BufferedImage based only on the location from the constants and a filename
     * this way you can handle the location in one place only and also I dont have to write so much when
     * laoding images in the future.
     * @param a the filename
      */
    public static BufferedImage loadImage(String a) throws IOException {
        BufferedImage image = null;
        if(!Objects.equals(Util.class.getResourceAsStream(Constants.LEVELP + a + ".png"), null)) {
            image = ImageIO.read(Objects.requireNonNull(Util.class.getResourceAsStream(Constants.LEVELP + a + ".png")));
        }
        if(!Objects.equals(Util.class.getResourceAsStream(Constants.TILEP + a + ".png"), null)) {
            image = ImageIO.read(Objects.requireNonNull(Util.class.getResourceAsStream(Constants.TILEP + a + ".png")));
        }
        if(!Objects.equals(Util.class.getResourceAsStream(Constants.GUIP + a + ".png"), null)) {
            image = ImageIO.read(Objects.requireNonNull(Util.class.getResourceAsStream(Constants.GUIP + a + ".png")));
        }
        if(!Objects.equals(Util.class.getResourceAsStream(Constants.SPRITEP + a + ".png"), null)) {
            image = ImageIO.read(Objects.requireNonNull(Util.class.getResourceAsStream(Constants.SPRITEP + a + ".png")));
        }
        return image;
    }



    private Util() {

    }
}
