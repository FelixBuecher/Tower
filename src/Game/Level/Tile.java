package Game.Level;

import Game.Graphics.Sprite;

public class Tile {

    private final Sprite sprite;
    private final boolean solid;
    private final boolean water;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
        solid = false;
        water = false;
    }

    public Tile(Sprite sprite, boolean solid) {
        this.sprite = sprite;
        this.solid = solid;
        water = false;
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
