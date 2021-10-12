package game.entity.mob;

import game.entity.Entity;
import game.graphics.Sprite;
import game.level.Level;
import game.util.Constants;

import static game.util.Util.abs;

import java.awt.image.BufferedImage;

public abstract class Mob extends Entity {

    protected Direction dir;
    protected double dx, dy;
    protected boolean left, right, up, down, shift;
    protected double maxHealth;
    protected double health;
    protected double maxMana;
    protected double mana;
    protected double attack;
    protected double moveSpeed;
    protected double attackSpeed;
    protected double armor;
    protected double magicResist;
    protected double critChance;
    protected double critDamage;
    protected double magicAttack;

    protected Sprite animDown;
    protected Sprite animUp;
    protected Sprite animLeft;
    protected Sprite animRight;
    protected BufferedImage[] sequence;

    public Mob(Sprite sprite, Level level){
        super(sprite, level);
        sequence = sprite.getSequence();
    }

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setShift(boolean b) {
        shift = b;
    }

    public void move(double xa, double ya) {

        if (xa != 0 && ya != 0) {
            move(0, ya);
            move(xa, 0);
            return;
        }

        if (xa > 0)
            dir = Direction.RIGHT;
        if (xa < 0)
            dir = Direction.LEFT;
        if (ya > 0)
            dir = Direction.DOWN;
        if (ya < 0)
            dir = Direction.UP;

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

    }

}
