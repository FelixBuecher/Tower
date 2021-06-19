package Game.Entity.Mob.Player;

import Game.Entity.Mob.Mob;
import Game.Graphics.Sprite;
import Game.Level.Level;

public class Player extends Mob {

    public Player(Sprite sprite, Level level) {
        super(sprite, level);
    }

    public int getLives() {
        return 0;
    }

    public int getHealth() {
        return 0;
    }

    public String getTimeToString() {
        return "";
    }

    public void update() {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
