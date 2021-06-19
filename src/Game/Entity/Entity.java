package Game.Entity;

import Game.Graphics.Sprite;
import Game.Level.Level;

import java.awt.*;

public class Entity {

    protected Sprite sprite;
    protected double x, y;
    protected int w, h, cw, ch;
    protected Level level;

    public Entity(Sprite sprite, Level level) {
        this.sprite = sprite;
        this.level = level;
    }

    public Entity() {

    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, cw, ch);
    }

    public boolean intersects(Entity e) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = e.getRectangle();
        return r1.intersects(r2);
    }

    public boolean intersects(Rectangle r) {
        return getRectangle().intersects(r);
    }

    public void setMapPosition() {

    }

    public void render(Graphics2D g) {

    }

    public void init(Level level) {
        this.level = level;
    }



}
