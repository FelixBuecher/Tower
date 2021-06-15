package Game.Tools;

/**
 * Integer vector class.
 *
 * @Author Felix Buecher
 * @Version 1.0
 */
public class Vector2i {

    private int x, y;

    public Vector2i() {
        set(0, 0);
    }

    public Vector2i(int x, int y) {
        set(x, y);
    }

    public Vector2i(Vector2i vector) {
        set(vector.getX(), vector.getY());
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void add(Vector2i vector) {
        x += vector.getX();
        y += vector.getY();
    }

    public void sub(Vector2i vector) {
        x -= vector.getX();
        y -= vector.getY();
    }

    public boolean equals(Object o) {
        return o instanceof Vector2i && isEqualTo((Vector2i) o);
    }

    public boolean isEqualTo(Vector2i v) {
        return v.getX() == getX() && v.getY() == getY();
    }
}
