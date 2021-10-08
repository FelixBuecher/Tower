package game.graphics;

import game.util.Constants;

import java.awt.image.BufferedImage;

/**
 * Sprite class to load single images from a spritesheet,
 * I could have probably done it together with spritesheet in a single class
 * but I prefer to have it separately.
 * Tile sprites are static initiated here and can be called from other classes
 * this way I only need to load them once and can call them as much as I like
 * without creating a new instance everytime.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Sprite {

    //////////////////////////////////////////////////////////////
    /////////////////////      Sprites       /////////////////////
    //////////////////////////////////////////////////////////////

    public static Sprite grass = createSprite(SpriteSheet.tileSheet,0,0);
    public static Sprite grassStone = createSprite(SpriteSheet.tileSheet,1,0);
    public static Sprite grassFlower0 = createSprite(SpriteSheet.tileSheet,2,0);
    public static Sprite voidSprite = createSprite(SpriteSheet.tileSheet,15,15);


    public static Sprite playerSprite = createSprite(SpriteSheet.playerSheet, 0, 0);
    public static Sprite playerSequence = createSequence(SpriteSheet.playerSheet, 0, 0, 4, 32, 32);

    public static Sprite banditAttackUp = createSprite(SpriteSheet.banditProjectile, 1, 0, 25, 25);
    public static Sprite banditAttackDown = createSprite(SpriteSheet.banditProjectile, 1, 2, 25, 25);
    public static Sprite banditAttackLeft = createSprite(SpriteSheet.banditProjectile, 0, 1, 25, 25);
    public static Sprite banditAttackRight = createSprite(SpriteSheet.banditProjectile, 2, 1, 25, 25);


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
        if(pixelPerfect) this.image = sheet.getImage().getSubimage(x, y, w, h);
        if(!pixelPerfect) this.image = sheet.getImage().getSubimage(x * w, y * h, w, h);
        sequence = new BufferedImage[1];
        sequence[0] = image;
    }

    private Sprite(SpriteSheet sheet, int x, int y, int numRight, int w, int h) {
        this.sheet = sheet;
        this.image = sheet.getImage().getSubimage(x, y, w, h);
        sequence = new BufferedImage[numRight];
        for(int i = 0; i < numRight; i++) {
            sequence[i] = this.sheet.getImage().getSubimage(x + i * w, y, w, h);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage[] getSequence() {
        return sequence;
    }

}
