package game.entity.projectiles;

import game.entity.Entity;
import game.graphics.Sprite;
import game.level.Level;

import static game.util.Util.abs;

public abstract class Projectile extends Entity {

    protected double xOri, yOri;
    protected double vx, vy, speed, angle;
    protected int animation;
    protected int range;
    protected boolean hit;

    protected Projectile(double x, double y, double dir, Sprite sprite, Level level) {
        super(sprite, level);
        xOri = x;
        yOri = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }

    protected void move(double xa, double ya) {

        if (collision((int) (x + xa), (int) (y + ya))) {
            remove = true;
        }

        if (xa != 0 && ya != 0) {
            move(0, ya);
            move(xa, 0);
            return;
        }

        while (xa != 0) {
            if (Math.abs(xa) >= 1) {
                if (!collision((int) (x + abs(xa)), (int) (y + ya))) {
                    this.x += abs(xa);
                }
                xa -= abs(xa);
            } else {
                if (!collision((int) (x + abs(xa)), (int) (y + ya))) {
                    this.x += xa;
                }
                xa = 0;
            }
        }
        while (ya != 0) {
            if (Math.abs(ya) >= 1) {
                if (!collision((int) (x + xa), (int) (y + abs(ya)))) {
                    this.y += abs(ya);
                }
                ya -= abs(ya);
            } else {
                if (!collision((int) (x + xa), (int) (y + abs(ya)))) {
                    this.y += ya;
                }
                ya = 0;
            }
        }
        double distance = Math.sqrt(Math.abs((xOri - x) * (xOri - x) + (yOri - y) * (yOri - y)));
        if (distance >= range) {
            remove = true;
        }

    }

    public boolean isHit() {
        return hit;
    }

    public void hit() {
        hit = true;
    }

}
