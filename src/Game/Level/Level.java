package Game.Level;

import Game.Tools.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Level {

    private double x, y;
    private int tileSize;
    private int w, h;
    private boolean shaking;
    private int intensity;
    private int[] tiles;
    private int yScroll, xScroll, xOffset, yOffset;
    private BufferedImage levelImage;


    public Level(String path, int tileSize) {
        this.tileSize = tileSize;
        loadLevel(path);
    }

    protected void loadLevel(String path) {
        try {
            levelImage = ImageIO.read(getClass().getResourceAsStream(path));
            this.w = levelImage.getWidth();
            this.h = levelImage.getHeight();
            tiles = new int[w * h];
            levelImage.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load file.");
        }
    }

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
        this.x += (x - this.x);
        this.y += (y - this.y);
        xScroll = (int) -this.x / tileSize;
        yScroll = (int) -this.y / tileSize;
    }

    public void render(Graphics2D g) {
        int x0 = xScroll - Constants.TILESIZE;
        int x1 = (xScroll + Constants.WIDTH) >> 3;
        int y0 = yScroll - Constants.TILESIZE;
        int y1 = (yScroll + Constants.HEIGHT) >> 3;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                g.drawImage(getTile(x, y).getSprite().getImage(), (int) this.x + x * tileSize, (int) this.y + y * tileSize, null);
            }
        }
    }
}
