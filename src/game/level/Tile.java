package game.level;

import game.graphics.Sprite;

/**
 * This class is used to create tiles in the game, so to speak
 * the world itself, the ground where you walk on and walls for example.
 * The tiles will be statically initiated in here so I only need to load them
 * once and just do a reference to it later.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Tile {

    //////////////////////////////////////////////////////////////
    /////////////////////       Tiles        /////////////////////
    //////////////////////////////////////////////////////////////

    public static Tile grass = createTile(Sprite.grass);
    public static Tile grassStone = createTile(Sprite.grassStone, true);
    public static Tile grassFlower0 = createTile(Sprite.grassFlower0);

    //////////////////////////////////////////////////////////////
    /////////////////////       Class        /////////////////////
    //////////////////////////////////////////////////////////////

    private final Sprite sprite;
    private final boolean solid;
    private final boolean water;

    /**
     * Used to create a tile with no attributes other than a sprite.
     * @param sprite the sprite for the tile
     * @return Tile
     */
    public static Tile createTile(Sprite sprite) {
        return new Tile(sprite, false, false);
    }

    /**
     * Used to create a tile and make it solid if solid = true
     * solid in the scope of the game means not walkable or
     * not able to attack through walls or this stuff.
     * @param sprite the sprite for the tile
     * @param solid if the tile is solid or not
     * @return Tile
     */
    public static Tile createTile(Sprite sprite, boolean solid) {
        return new Tile(sprite, solid, false);
    }

    /**
     * Used to create a tile and make it solid if solid = true
     * or to make it a water tile, it can also be solid and water
     * @param sprite the sprite for the tile
     * @param solid if the tile is solid or not
     * @param water if the tile is a water tile
     * @return Tile
     */
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
