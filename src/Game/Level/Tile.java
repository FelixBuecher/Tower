package Game.Level;

import Game.Graphics.Sprite;

public class Tile {

    public static Tile grass = new Tile(Sprite.grass);
    public static Tile grassStone = new Tile(Sprite.grassStone, true);
    public static Tile grassFlower0 = new Tile(Sprite.grassFlower0);
    public static Tile voidTile = new Tile(Sprite.voidSprite, true);

    private Sprite sprite;
    private boolean solid, water;

    public Tile(Sprite sprite) {
        new Tile(sprite, false, false);
    }

    public Tile(Sprite sprite, boolean solid) {
        new Tile(sprite, solid, false);
    }

    public Tile(Sprite sprite, boolean solid, boolean water) {
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
