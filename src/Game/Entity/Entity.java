package Game.Entity;

import Game.Graphics.Sprite;
import Game.Level.Level;
import Game.Tools.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is the general entity class, that is used to to create basic
 * objects in the game, like interactable stuff.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Entity {

    protected Sprite sprite;
    protected BufferedImage image;
    protected Level level;
    protected double x, y;
    protected int w, h, cw, ch; // Dimensions and collision dimensions

    public Entity(Sprite sprite, Level level) {
        this.sprite = sprite;
        this.level = level;
        this.image = sprite.getImage();
        w = image.getWidth();
        h = image.getHeight();
        cw = image.getWidth();
        ch = image.getHeight();
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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

    public void render(Graphics2D g) {
        g.drawImage(image,  (int) (x - level.getX() - cw / 2), (int) (y - level.getY() -ch / 2), null);

        // Draw collision hitbox
//        g.setColor(Color.RED);
//        Rectangle r = getRectangle();
//        r.x -= level.getX() + cw / 2;
//        r.y -= level.getY() + ch / 2;
//        g.draw(r);
    }

    public boolean collision(double x, double y) {
        // Default is no collision
        boolean solid = false;

        // Getting tiles near the corners of the entity that is checked
        int leftT = ((int) Math.ceil(x - ch / 2)) / Constants.TILESIZE;
        int rightT = ((int) Math.ceil(x + cw / 2 - 1)) / Constants.TILESIZE;
        int topT = ((int) Math.ceil(y - ch / 2)) / Constants.TILESIZE;
        int botT = ((int) Math.ceil(y + ch / 2 -1)) / Constants.TILESIZE;

        int xCol = (int) Math.ceil(cw / Constants.TILESIZE - 1);
        int yCol = (int) Math.ceil(ch / Constants.TILESIZE - 1);

        // Checking for collisions between tiles, if the entity is bigger than the normal tilesize
        for(int i = 0; i<=xCol; i++) {
            for(int j = 0; j<=yCol; j++) {
                if(level.getTile(rightT-i, topT).isSolid() || level.getTile(leftT, topT+j).isSolid() || level.getTile(rightT, botT-j).isSolid()
                        || level.getTile(leftT+i, botT).isSolid()) {
                    solid = true;
                }
            }
        }
        return solid;
    }



}
