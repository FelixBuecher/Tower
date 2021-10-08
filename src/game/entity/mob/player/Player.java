package game.entity.mob.player;

import game.entity.mob.Mob;
import game.entity.projectiles.Testprojectiles;
import game.gamestate.GameLevel;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.MouseHandler;
import game.level.Level;
import game.util.Constants;

import java.awt.*;

public class Player extends Mob {

    public static int fr = 5;

    public Player(Sprite sprite, Level level) {
        super(sprite, level);
        animDown = Sprite.createSequence(SpriteSheet.playerSheet, 0, 0, 4, 32, 32);
        animUp = Sprite.createSequence(SpriteSheet.playerSheet, 0, 3*32, 4, 32, 32);
        animLeft = Sprite.createSequence(SpriteSheet.playerSheet, 0, 32, 4, 32, 32);
        animRight = Sprite.createSequence(SpriteSheet.playerSheet, 0, 2*32, 4, 32, 32);
        health = 5;
        maxHealth = 5;
        setCollision(26, 31);
//        setDrawCollision();
    }

    public void reduceHealth() {
        health -= 1;
    }

    public void update() {
        move();

        if(fr > 0) {
            fr--;
        }
    }

    int frame = 0;
    private void move() {
        double xa = 0, ya = 0;


        if (shift) moveSpeed = 6; else moveSpeed = 4;
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

    public void shoot(MouseHandler mouse, GameLevel gl) {
        if (fr <= 0) {
            double dx = (mouse.getX() - (Constants.width / 2.0 * Constants.scale));
            double dy = (mouse.getY() - (Constants.height / 2.0 * Constants.scale));
            double dir = Math.atan2(dy, dx);
            Testprojectiles test = new Testprojectiles(getX(), getY(), dir, Sprite.banditAttackDown, level);
            gl.add(test);
            fr = 10;
        }

    }
}
