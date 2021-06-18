package Game.Entity;

import Game.Graphics.Sprite;
import Game.Level.Level;
import Game.Tools.Position;

import java.awt.*;

public class Entity {

    protected Sprite sprite;
    protected Position pos;
    protected int w, h, cw, ch;
    protected Level level;

    public Entity(Sprite sprite, Level level) {
        this.sprite = sprite;
        this.level = level;
    }

    public Entity() {

    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) pos.getX(), (int) pos.getY(), cw, ch);
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
