package Game.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SpriteSheet {

    //////////////////////////////////////////////////////////////
    ///////////////////// Tile sprite sheets /////////////////////
    //////////////////////////////////////////////////////////////

    public static SpriteSheet tileSheet = new SpriteSheet("/Textures/Tiles/spritesheet.png");



    private final int w, h;
    private BufferedImage image;
    private BufferedImage[] sequence;

    // Create a buffered image from path
    public SpriteSheet(String path) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch(Exception e) {
            e.printStackTrace();
        }
        this.w = image.getWidth();
        this.h = image.getHeight();
    }

    // Create a subspritesheet from a spritesheet and fill the sequence array
    // boolean createSequence is mainly used to create a image array for animations
    public SpriteSheet(SpriteSheet sheet, int x, int y, int numLeft, int width, int height, boolean createSequence) {
        this.w = width * numLeft;
        this.h = height;
        BufferedImage seq = sheet.image.getSubimage(x * width, y * height, w, h);
        if(createSequence) {
            sequence = new BufferedImage[numLeft];
            for(int i = 0; i < numLeft; i++) {
                sequence[i] = seq.getSubimage(x + i, y, width, height);
            }
        }
    }

    // Can be null if it is not initialized
    public BufferedImage[] getSequence() {
        return sequence;
    }

    public BufferedImage getImage() {
        return image;
    }

}
