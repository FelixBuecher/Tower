package Game.GameState;

import Game.Audio.JukeBox;
import Game.Entity.Mob.Player.Player;
import Game.GUI.GUI;
import Game.Graphics.Sprite;
import Game.Graphics.SpriteSheet;
import Game.Input.Key;
import Game.Level.Level;
import Game.Tools.Constants;

import java.awt.*;

public class Level1State extends GameState {

    private Player player;
    private Level level;
    private GUI gui;
    private boolean blockInput = false;


    public Level1State(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        level = new Level("/Textures/Level/level.png", 16);

        player = new Player(Sprite.playerSprite, level);

        //gui = new GUI(player);

        JukeBox.load("/Audio/Music/level1.mp3", "level1");
        JukeBox.loop("level1", Constants.VOLUME);
    }

    @Override
    public void update() {
        handleInput();
        player.update();
        //level.setPosition(new Position(Constants.WIDTH / 2 - player.getPos().getX(), Constants.HEIGHT / 2 - player.getPos().getY()));
        //level.update();
    }

    @Override
    public void render(Graphics2D g) {
        //level.render(g);

        player.render(g);

        //gui.render(g);
    }

    @Override
    public void handleInput() {
        if(Key.isPressed(Key.ESCAPE)) gsm.pause();
        if(blockInput || player.getHealth() == 0) return;
//        player.setUp(Key.key[Key.UP]);
//        player.setLeft(Key.key[Key.LEFT]);
//        player.setDown(Key.key[Key.DOWN]);
//        player.setRight(Key.key[Key.RIGHT]);
//        player.setJumping(Key.key[Key.BUTTON1]);
//        player.setDashing(Key.key[Key.BUTTON2]);
//        if(Key.isPressed(Key.BUTTON3)) player.setAttacking();
//        if(Key.isPressed(Key.BUTTON4)) player.setCharging();
    }
}
