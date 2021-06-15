package Game.Tools;

/**
 * Handler for the position of gameobjects.
 *
 * @Author Felix Buecher
 * @Version 1.0
 */
public class Position {

    private double x;
    private double y;

    public Position(double x, double y){
        setPosition(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addX(double x) {
        this.x += x;
    }

    public void addY(double y) {
        this.y += y;
    }

    public void addPosition(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public Position addGet(double x, double y) {
        addPosition(x, y);
        return this;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position setGet(double x, double y) {
        setPosition(x, y);
        return this;
    }
}
