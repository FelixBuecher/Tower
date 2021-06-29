package game.entity.Mob.Player;

import game.entity.Mob.Mob;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.level.Level;

import java.awt.*;

public class Player extends Mob {

    public Player(Sprite sprite, Level level) {
        super(sprite, level);
        animDown = Sprite.createSequence(SpriteSheet.playerSheet, 0, 0, 4, 32, 32);
        animUp = Sprite.createSequence(SpriteSheet.playerSheet, 0, 3*32, 4, 32, 32);
        animLeft = Sprite.createSequence(SpriteSheet.playerSheet, 0, 32, 4, 32, 32);
        animRight = Sprite.createSequence(SpriteSheet.playerSheet, 0, 2*32, 4, 32, 32);
        health = 5;
        maxHealth = 5;
        setCollision(25, 30);
        setDrawCollision();
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
            if (frame == 0) image = sequence[0];
            if (frame % 8 == 0) image = sequence[1];
            if (frame % 16 == 0) image = sequence[2];
            if (frame % 24 == 0) image = sequence[3];
            if (frame % 32 == 0) image = sequence[2];
            if (frame > 32) frame = 0;
        } else {
            image = sequence[0];
            frame = 0;
        }
    }



    @Override
    public void render(Graphics2D g) {
        g.drawImage(image,  (int) (x - level.getX() - cw / 2), (int) (y - level.getY() -ch / 2), null);

        if (drawCollision) {
            g.setStroke(new BasicStroke(2));
            g.setColor(Color.RED);
            Rectangle r = getRectangle();
            r.x -= level.getX() + cw / 2;
            r.y -= level.getY() + ch / 2;
            g.draw(r);
        }
    }
}
