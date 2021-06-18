package Game.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SpriteSheet {

    //////////////////////////////////////////////////////////////
    ///////////////////// Tile sprite sheets /////////////////////
    //////////////////////////////////////////////////////////////

    public static SpriteSheet tileSheet = new SpriteSheet("/Textures/Tiles/spritesheet.png");


    //////////////////////////////////////////////////////////////
    /////////////////////       Class        /////////////////////
    //////////////////////////////////////////////////////////////

    private final int w, h;
    private BufferedImage sheet;

    /**
     * Crate a spritesheet from a given path
     * @param path  the directory of the image that shall be used to get a spritesheet
     */
    public SpriteSheet(String path) {
        try {
            sheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch(Exception e) {
            e.printStackTrace();
        }
        this.w = sheet.getWidth();
        this.h = sheet.getHeight();
    }

    /**
     * Create a spritesheet of a spritesheet, this way I can load bigger spritesheets and get everything from an already loaded sheet
     * @param sheet the spritesheet to be used
     * @param x the x position on the spritesheet
     * @param y the y position on the spritesheet
     * @param numRight  number of sprites to get, reading left to right
     * @param width the with of the sprites
     * @param height the height of the sprites
     */
    public SpriteSheet(SpriteSheet sheet, int x, int y, int numRight, int width, int height) {
        this.w = width * numRight;
        this.h = height;
        this.sheet = sheet.sheet.getSubimage(x * width, y * height, w, h);
    }

    public BufferedImage getSheet() {
        return sheet;
    }
}
