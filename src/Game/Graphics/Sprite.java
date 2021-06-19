package Game.Graphics;

import Game.Tools.Constants;

import java.awt.image.BufferedImage;

public class Sprite {

    //////////////////////////////////////////////////////////////
    /////////////////////    Tile sprites    /////////////////////
    //////////////////////////////////////////////////////////////

    public static Sprite grass = createSprite(SpriteSheet.tileSheet,0,0);
    public static Sprite grassStone = createSprite(SpriteSheet.tileSheet,1,0);
    public static Sprite grassFlower0 = createSprite(SpriteSheet.tileSheet,2,0);
    public static Sprite voidSprite = createSprite(SpriteSheet.tileSheet,15,15);


    public static Sprite playerSprite = createSprite(SpriteSheet.playerSheet, 0, 0, 16, 16);
    public static Sprite playerSequence = createSequence(SpriteSheet.playerSheet, 0, 0, 4, 16, 16);

    //////////////////////////////////////////////////////////////
    /////////////////////       Class        /////////////////////
    //////////////////////////////////////////////////////////////

    protected SpriteSheet sheet;
    protected BufferedImage[] sequence;
    protected BufferedImage image;

    /**
     * Create a single sprite with normal tilesize (Constants)
     * @param sheet the sheet where to get the sprite from
     * @param x the x position on the sheet
     * @param y the y position on the sheet
     */
    public static Sprite createSprite(SpriteSheet sheet, int x, int y) {
        return new Sprite(sheet, x, y, Constants.TILESIZE, Constants.TILESIZE, false);
    }

    /**
     * Create a single sprite from a spritesheet
     * @param sheet the spritesheet to use
     * @param x the x position on the spritesheet
     * @param y the y position on the spritesheet
     * @param w the width of the sprite
     * @param h the height of the sprite
     */
    public static Sprite createSprite(SpriteSheet sheet, int x, int y, int w, int h) {
        return new Sprite(sheet, x, y, w, h, false);
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
    public static Sprite createSprite(SpriteSheet sheet, int x, int y, int w, int h, boolean pixelPerfect) {
        return new Sprite(sheet, x, y, w, h, pixelPerfect);
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
    public static Sprite createSequence(SpriteSheet sheet, int x, int y, int numRight, int w, int h) {
        return new Sprite(sheet, x, y, numRight, w, h);
    }

    private Sprite(SpriteSheet sheet, int x, int y, int w, int h, boolean pixelPerfect) {
        this.sheet = sheet;
        if(pixelPerfect) this.image = this.sheet.getSheet().getSubimage(x, y, w, h);
        if(!pixelPerfect) this.image = this.sheet.getSheet().getSubimage(x * w, y * h, w, h);
    }

    private Sprite(SpriteSheet sheet, int x, int y, int numRight, int w, int h) {
        sequence = new BufferedImage[numRight];
        this.sheet = sheet;
        for(int i = 0; i < numRight; i++) {
            sequence[i] = this.sheet.getSheet().getSubimage(x + i * w, y, w, h);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage[] getSequence() {
        return sequence;
    }
}
