package Game.Entity.Mob.Player;

import Game.Entity.Mob.Mob;
import Game.Graphics.Sprite;
import Game.Tools.Position;

public class Player extends Mob {

    public Player(Sprite sprite) {
        super(sprite);
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

    public Position getPos() {
        return pos;
    }
}
