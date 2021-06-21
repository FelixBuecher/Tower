package Game.Entity.Mob.Player;

import Game.Entity.Mob.Mob;
import Game.Graphics.Sprite;
import Game.Level.Level;

public class Player extends Mob {

    public Player(Sprite sprite, Level level) {
        super(sprite, level);
    }

    public int getHealth() {
        return 5;
    }

    public String getTimeToString() {
        return "1:23";
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
