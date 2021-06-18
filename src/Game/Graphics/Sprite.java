package Game.Graphics;

import Game.Tools.Constants;

import java.awt.image.BufferedImage;

public class Sprite {

    //////////////////////////////////////////////////////////////
    /////////////////////    Tile sprites    /////////////////////
    //////////////////////////////////////////////////////////////

    public static Sprite grass = new Sprite(SpriteSheet.tileSheet,0,0);
    public static Sprite grassStone = new Sprite(SpriteSheet.tileSheet,1,0);
    public static Sprite grassFlower0 = new Sprite(SpriteSheet.tileSheet,2,0);
    public static Sprite voidSprite = new Sprite(SpriteSheet.tileSheet,15,15);


    public static Sprite playerSprite = new Sprite(SpriteSheet.playerSheet, 0, 0);

    //////////////////////////////////////////////////////////////
    /////////////////////       Class        /////////////////////
    //////////////////////////////////////////////////////////////

    public int w, h;
    protected SpriteSheet sheet;
    protected BufferedImage[] sequence;
    protected BufferedImage image;

    /**
     * Create a single sprite with normal tilesize (Constants)
     * @param sheet the sheet where to get the sprite from
     * @param x the x position on the sheet
     * @param y the y position on the sheet
     */
    public Sprite(SpriteSheet sheet, int x, int y) {
        new Sprite(sheet, x, y, Constants.TILESIZE, Constants.TILESIZE);
    }

    /**
     * Create a single sprite from a spritesheet
     * @param sheet the spritesheet to use
     * @param x the x position on the spritesheet
     * @param y the y position on the spritesheet
     * @param w the width of the sprite
     * @param h the height of the sprite
     */
    public Sprite(SpriteSheet sheet, int x, int y, int w, int h) {
        new Sprite(sheet, x, y, w, h, false);
    }

    /**
     * Create a single sprite from a spritesheet
     * @param sheet the spritesheet to use
     * @param x the x position on the spritesheet
     * @param y the y position on the spritesheet
     * @param w the width of the sprite
     * @param h the height of the sprite
     * @param pixelPerfect if you want to calculate x and y on your own
     */
    public Sprite(SpriteSheet sheet, int x, int y, int w, int h, boolean pixelPerfect) {
        this.sheet = sheet;
        this.w = w;
        this.h = h;
        if(pixelPerfect) image = sheet.getSheet().getSubimage(x, y, w, h);
        if(!pixelPerfect) image = sheet.getSheet().getSubimage(x * w, y * h, w, h);
    }

    /**
     * Create an image array, best used for animation
     * @param sheet the spritesheet to use
     * @param x the x position on the spritesheet
     * @param y the y position on the spritesheet
     * @param numRight the number of tiles to the right that shall be added to the image arraay
     * @param w the width of the sprite
     * @param h the height of the sprite
     */
    public Sprite(SpriteSheet sheet, int x, int y, int numRight, int w, int h) {
        sequence = new BufferedImage[numRight];
        for(int i = 0; i < numRight; i++) {
            sequence[i] = sheet.getSheet().getSubimage(x + i, y, w, h);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage[] getSequence() {
        return sequence;
    }
}
