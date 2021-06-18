package Game.Entity.Mob;

import Game.Entity.Entity;
import Game.Graphics.Sprite;
import Game.Level.Level;

public abstract class Mob extends Entity {

    protected Direction dir;

    public Mob(Sprite sprite, Level level){
        super(sprite, level);
    }
    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

}
