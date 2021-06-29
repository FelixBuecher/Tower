package game.gamestate;

import game.entity.Mob.Player.Player;
import game.gui.GUI;
import game.gui.Textbox;
import game.input.Key;
import game.level.Level;

import java.awt.*;

public abstract class GameLevel extends GameState {

    protected Player player;
    protected Level botLayer;
    protected Level topLayer;
    protected GUI gui;
    protected Textbox text;
    protected boolean showText;
    protected boolean blockInput = false;

    public GameLevel(GameStateManager gsm) {
        super(gsm);
        init();
    }

    public void init() {

    }

    public void update() {
        handleInput();
        if (player != null) {
            player.update();
        }
        if (botLayer != null) {
            botLayer.setPosition(player.getX() - (float) hWidth, player.getY() - (float) hHeight);
            botLayer.update();
        }
        if (topLayer != null) {
            topLayer.setPosition(player.getX() - (float) hWidth, player.getY() - (float) hHeight);
            topLayer.update();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (botLayer != null) botLayer.render(g);
        if (player != null) player.render(g);
        if (topLayer != null) topLayer.render(g);
        if (gui != null) gui.render(g);
        if (showText) text.render(g);
    }

    public void text() {
        showText = !showText;
    }

    @Override
    public void handleInput() {
        if(blockInput || player.getHealth() == 0) return;
        if(Key.isPressed(Key.ESCAPE)) gsm.setState(GameStateManager.LEVEL1STATE);
        player.setUp(Key.key[Key.UP]);
        player.setLeft(Key.key[Key.LEFT]);
        player.setDown(Key.key[Key.DOWN]);
        player.setRight(Key.key[Key.RIGHT]);
        if(Key.isPressed(Key.SHIFT)) text();
//        player.setShift(Key.key[Key.SHIFT]);
//        if(Key.isPressed(Key.E_KEY)) player.reduceHealth();
        if(Key.isPressed(Key.E_KEY)) gsm.setState(GameStateManager.LEVEL2STATE);
//        player.setJumping(Key.key[Key.BUTTON1]);
//        player.setDashing(Key.key[Key.BUTTON2]);
//        if(Key.isPressed(Key.BUTTON3)) player.setAttacking();
//        if(Key.isPressed(Key.BUTTON4)) player.setCharging();
    }

}
