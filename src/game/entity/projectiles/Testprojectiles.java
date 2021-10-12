package game.entity.projectiles;

import game.graphics.Sprite;
import game.level.Level;
import game.util.Constants;

import java.awt.*;

public class Testprojectiles extends Projectile {

    public Testprojectiles(double x, double y, double dir, Sprite sprite, Level level) {
        super(x, y, dir, sprite, level);
        animation = 0;
        speed = 5;
        range = 200;
        vx = speed * Math.cos(angle);
        vy = speed * Math.sin(angle);
        setCollision(23, 23);
    }

    public void update() {
        move(vx, vy);
        if (animation < 500) {
            animation += speed + random.nextInt(5);
        } else {
            animation = random.nextInt(15);
        }
    }

    public void render(Graphics2D g) {
        if (animation % 40 > 30)
            g.drawImage(Sprite.banditAttackUp.getImage(),  (int) (x - level.getX() - cw / 2), (int) (y - level.getY() -ch / 2), null);
        else if (animation % 40 > 20)
            g.drawImage(Sprite.banditAttackRight.getImage(),  (int) (x - level.getX() - cw / 2), (int) (y - level.getY() -ch / 2), null);
        else if (animation % 40 > 10)
            g.drawImage(Sprite.banditAttackDown.getImage(),  (int) (x - level.getX() - cw / 2), (int) (y - level.getY() -ch / 2), null);
        else
            g.drawImage(Sprite.banditAttackLeft.getImage(),  (int) (x - level.getX() - cw / 2), (int) (y - level.getY() -ch / 2), null);

        if (drawCollision) {
            g.setColor(Color.RED);
            Rectangle r = getRectangle();
            r.x -= level.getX() + cw / 2;
            r.y -= level.getY() + ch / 2;
            g.draw(r);
        }
    }
}
