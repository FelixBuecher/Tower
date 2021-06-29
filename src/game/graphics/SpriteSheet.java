package game.graphics;

import java.awt.image.BufferedImage;

import static game.tools.Util.loadImage;

/**
 * As stated in the sprite class I chose to make this separately
 * so this class is used to load complete tilesheets or spritesheets
 * and I prefer not to use a whole tilesheet in the game I rather use
 * single images so this is basically just a class for sprite.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class SpriteSheet {

    //////////////////////////////////////////////////////////////
    /////////////////////   Sprite sheets    /////////////////////
    //////////////////////////////////////////////////////////////

    public static SpriteSheet tileSheet = new SpriteSheet("spritesheet32");
    public static SpriteSheet playerSheet = new SpriteSheet("character32");

    //////////////////////////////////////////////////////////////
    /////////////////////       Class        /////////////////////
    //////////////////////////////////////////////////////////////

    private BufferedImage image;

    /**
     * Crate a spritesheet from a given path.
     * @param path  the directory of the image
     *              that shall be used to get a spritesheet
     */
    public SpriteSheet(final String path) {
        try {
            image = loadImage(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a spritesheet of a spritesheet, this way
     * I can load bigger spritesheets and get everything
     * from an already loaded sheet.
     * @param sheet the spritesheet to be used
     * @param x the x position on the spritesheet
     * @param y the y position on the spritesheet
     * @param numRight  number of sprites to get,
     *                 reading left to right
     * @param width the with of the sprites
     * @param height the height of the sprites
     */
    public SpriteSheet(SpriteSheet sheet, int x, int y,
                       int numRight, int width, int height) {
        image = sheet.getImage().getSubimage(x * width, y * height,
                width * numRight, height);
    }

    public BufferedImage getImage() {
        return image;
    }
}
