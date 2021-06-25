package Game.GameState;

import Game.Audio.JukeBox;
import Game.Entity.Mob.Player.Player;
import Game.GUI.GUI;
import Game.Graphics.Sprite;
import Game.Input.Key;
import Game.Level.Level;
import Game.Tools.Constants;

import java.awt.*;

/**
 * The first level of the game, for now its just a test level
 * but I will use this class to extend and create more levels with it.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Level1 extends GameState {

    private Player player;
    private Level level;
    private GUI gui;
    private boolean blockInput = false;


    public Level1(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        level = new Level("level", Constants.TILESIZE);

        player = new Player(Sprite.playerSprite, level);
        player.setPosition(50, 50);

        gui = new GUI(player);
        JukeBox.load("level1", "level1", false);
        JukeBox.loop("level1", Constants.VOLUME);
    }

    @Override
    public void update() {
        handleInput();
        player.update();
        level.setPosition(player.getX() - (float) Constants.WIDTH / 2, player.getY() - (float) Constants.HEIGHT / 2);
        level.update();
    }

    @Override
    public void render(Graphics2D g) {
        level.render(g);

        player.render(g);

        gui.render(g);
    }

    @Override
    public void handleInput() {
        if(Key.isPressed(Key.ESCAPE)) gsm.pause();
//        if(blockInput || player.getHealth() == 0) return;
        player.setUp(Key.key[Key.UP]);
        player.setLeft(Key.key[Key.LEFT]);
        player.setDown(Key.key[Key.DOWN]);
        player.setRight(Key.key[Key.RIGHT]);
        player.setShift(Key.key[Key.SHIFT]);
        if(Key.isPressed(Key.E_KEY)) player.reduceHealth();
//        player.setJumping(Key.key[Key.BUTTON1]);
//        player.setDashing(Key.key[Key.BUTTON2]);
//        if(Key.isPressed(Key.BUTTON3)) player.setAttacking();
//        if(Key.isPressed(Key.BUTTON4)) player.setCharging();
    }
}
