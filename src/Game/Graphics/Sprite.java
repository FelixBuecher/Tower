package Game.Graphics;

import Game.Tools.Constants;

public class Sprite {

    //////////////////////////////////////////////////////////////
    /////////////////////    Tile sprites    /////////////////////
    //////////////////////////////////////////////////////////////

    public static Sprite grass = new Sprite(SpriteSheet.tileSheet,0,0);
    public static Sprite grassStone = new Sprite(SpriteSheet.tileSheet,1,0);
    public static Sprite grassFlower0 = new Sprite(SpriteSheet.tileSheet,2,0);
    public static Sprite grassFlower1 = new Sprite(SpriteSheet.tileSheet,3,0);
    public static Sprite grassFlower2 = new Sprite(SpriteSheet.tileSheet,4,0);
    public static Sprite voidSprite = new Sprite(SpriteSheet.tileSheet,15,15);


    public int w, h;
    protected SpriteSheet sheet;

    // Create a single sprite with the normal tilesize (
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
        if(pixelPerfect) sheet.getImage().getSubimage(x, y, w, h);
        if(!pixelPerfect) sheet.getImage().getSubimage(x * w, y * h, w, h);
    }

}
