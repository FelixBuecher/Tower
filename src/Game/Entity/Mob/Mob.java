package Game.Entity.Mob;

import Game.Entity.Entity;
import Game.Graphics.Sprite;

public abstract class Mob extends Entity {

    protected Direction dir;

    public Mob(Sprite sprite) {
        super(sprite);
    }

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

}
