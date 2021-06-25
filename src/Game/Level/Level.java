package Game.Level;

import Game.Tools.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * This class is used to load playable levels, the way I wanted to create levels
 * was to make it simple and fast so I can add many levels without making it a hassle
 * so I managed to create a method of drawing levels and turning them into playable
 * levels. The way I do this is by having easy tile color coded, so each pixel in
 * the image that is used for the level is 1 tile in the game and that tile is based
 * on the color of that pixel. This allows me to create levels easy and fast and
 * pretty visually.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Level {

    private double x, y;
    private final int tileSize;
    private int w, h;
    private boolean shaking;
    private int intensity;
    private int[] tiles;

    /**
     * Used to create a level based on an image.
     * @param path the path to the image for the level
     * @param tileSize the tilesize that shall be used for the level
     */
    public Level(String path, int tileSize) {
        this.tileSize = tileSize;
        loadLevel(path);
    }

    /**
     * Used to create a level based on an image, this function will use the normal tilesize.
     * @param path the path to the image for the level
     */
    public Level(String path) {
        this.tileSize = Constants.TILESIZE;
        loadLevel(path);
    }

    private void loadLevel(String path) {
        try {
            BufferedImage levelImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Constants.LEVELP + path + ".png")));
            this.w = levelImage.getWidth();
            this.h = levelImage.getHeight();
            tiles = new int[w * h];
            // I am filling an array with the color values of the level image
            // so I can later retrieve these values and based on the corresponding
            // color render the corresponding tile
            levelImage.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load file.");
        }
    }

    /**
     * Used to determine the type of tile at the desired location.
     * @param x the x position of the tile
     * @param y the y position of the tile
     * @return the Tile object at the position
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= w || y < 0 || y >= h)
            return Tile.voidTile;
        if (tiles[x + y * w] == 0xFF00FF00)
            return Tile.grass;
        if (tiles[x + y * w] == 0xFF7F7F00)
            return Tile.grassStone;
        if (tiles[x + y * w] == 0xFFFFFF00)
            return Tile.grassFlower0;
        return Tile.voidTile;
    }

    public void update() {
        if(shaking) {
            this.x += Math.random() * intensity - (intensity >> 2);
            this.y += Math.random() * intensity - (intensity >> 2);
        }
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * For rendering the level (Tiles)
     * @param g Graphics2D
     */
    public void render(Graphics2D g) {
        // I keep it flexible because I do not know how big I want the normal tilesize to be yet
        int x0 = (int) (x - tileSize) / tileSize ; // -tilesize so I can render 1 row further than what I can see so it becomes smoother
        int x1 = (int) (x + Constants.WIDTH + tileSize) / tileSize; // +tilesize for the same reason
        int y0 = (int) (y - tileSize) / tileSize;
        int y1 = (int) (y + Constants.HEIGHT + tileSize) / tileSize;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                // Basically I will get every single tile position that I can currently see on the screen and only render those tiles
                // this way I can maximize my fps and keep a very smooth picture
                g.drawImage(getTile(x, y).getSprite().getImage(), (x * tileSize) - (int) this.x, (y * tileSize) - (int) this.y, null);
            }
        }

    }
}