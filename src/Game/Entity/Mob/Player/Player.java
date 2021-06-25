package Game.Entity.Mob.Player;

import Game.Entity.Mob.Mob;
import Game.Graphics.Sprite;
import Game.Graphics.SpriteSheet;
import Game.Level.Level;

public class Player extends Mob {

    private boolean walking = false;

    public Player(Sprite sprite, Level level) {
        super(sprite, level);
        animDown = Sprite.createSequence(SpriteSheet.playerSheet, 0, 0, 4, 32, 32);
        animUp = Sprite.createSequence(SpriteSheet.playerSheet, 0, 3*32, 4, 32, 32);
        animLeft = Sprite.createSequence(SpriteSheet.playerSheet, 0, 32, 4, 32, 32);
        animRight = Sprite.createSequence(SpriteSheet.playerSheet, 0, 2*32, 4, 32, 32);
        health = 5;
        maxHealth = 5;
    }

    public void reduceHealth() {
        health -= 1;
    }

    public void update() {
        move();
    }

    int frame = 0;
    private void move() {
        double xa = 0, ya = 0;


        if (shift) moveSpeed = 6;
        if (!shift) moveSpeed = 4;
        if (right) {
            sequence = animRight.getSequence();
            xa += moveSpeed;
        }
        if (left) {
            sequence = animLeft.getSequence();
            xa -= moveSpeed;
        }
        if (up) {
            sequence = animUp.getSequence();
            ya -= moveSpeed;
        }
        if (down) {
            sequence = animDown.getSequence();
            ya += moveSpeed;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            frame++;
            walking = true;
            if (frame == 0) image = sequence[0];
            if (frame % 8 == 0) image = sequence[1];
            if (frame % 16 == 0) image = sequence[2];
            if (frame % 24 == 0) image = sequence[3];
            if (frame % 32 == 0) image = sequence[2];
            if (frame > 32) frame = 0;
        } else {
            image = sequence[0];
            frame = 0;
            walking = false;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
