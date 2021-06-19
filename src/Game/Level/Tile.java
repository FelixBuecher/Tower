package Game.Level;

import Game.Graphics.Sprite;

public class Tile {

    public static Tile grass = createTile(Sprite.grass);
    public static Tile grassStone = createTile(Sprite.grassStone, true);
    public static Tile grassFlower0 = createTile(Sprite.grassFlower0);
    public static Tile voidTile = createTile(Sprite.voidSprite, true);

    private Sprite sprite;
    private boolean solid, water;

    public static Tile createTile(Sprite sprite) {
        return new Tile(sprite, false, false);
    }

    public static Tile createTile(Sprite sprite, boolean solid) {
        return new Tile(sprite, solid, false);
    }

    public static Tile createTile(Sprite sprite, boolean solid, boolean water) {
        return new Tile(sprite, solid, water);
    }

    private Tile(Sprite sprite, boolean solid, boolean water) {
        this.sprite = sprite;
        this.solid = solid;
        this.water = water;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isWater() {
        return water;
    }

}
