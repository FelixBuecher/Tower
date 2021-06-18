package Game.Level;

import Game.Tools.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Level {

    private Position pos;
    private int tileSize;
    private int w, h;
    private boolean shaking;
    private int intensity;
    private int[] tiles;
    private int rowOffset;
    private int colOffset;
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
            pos.addX(Math.random() * intensity - ((int) intensity >> 2));
            pos.addY(Math.random() * intensity - ((int) intensity >> 2));
        }
    }

    public void setPosition(Position pos) {
        this.pos.addPosition(pos.getX() - this.pos.getX(), pos.getY() - this.pos.getY());

        colOffset = (int) -this.pos.getX() / tileSize;
        rowOffset = (int) -this.pos.getY() / tileSize;

    }

    public void render(Graphics2D g) {
//        for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
//
//            if(row >= numRows) break;
//
//            for(int col = colOffset; col < colOffset + numColsToDraw; col++) {
//
//                if(col >= numCols) break;
//                if(map[row][col] == 0) continue;
//
//                int rc = map[row][col];
//                int r = rc / numTilesAcross;
//                int c = rc % numTilesAcross;
//
//                g.drawImage(tiles[r][c].getImage(),(int)x + col * tileSize,(int)y + row * tileSize,null);
//
//            }
//
//        }
    }
}
