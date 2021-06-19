package Game.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    //////////////////////////////////////////////////////////////
    ///////////////////// Tile sprite sheets /////////////////////
    //////////////////////////////////////////////////////////////

    public static SpriteSheet tileSheet = new SpriteSheet(  "/Textures/Tiles/spritesheet.png");
    public static SpriteSheet playerSheet = new SpriteSheet("/Textures/Sprites/character.png");
    public static SpriteSheet test = new SpriteSheet(playerSheet, 0, 0, 1, 16, 16);
    //////////////////////////////////////////////////////////////
    /////////////////////       Class        /////////////////////
    //////////////////////////////////////////////////////////////

    private BufferedImage sheet;

    /**
     * Crate a spritesheet from a given path
     * @param path  the directory of the image that shall be used to get a spritesheet
     */
    public SpriteSheet(String path) {
        try {
            sheet = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
        } catch(Exception e) {
            e.printStackTrace();
        }
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
        this.sheet = sheet.sheet.getSubimage(x * width, y * height, width * numRight, height);
    }

    public BufferedImage getSheet() {
        return sheet;
    }
}
