package game.gamestate;

import game.entity.mob.player.Player;
import game.entity.projectiles.Projectile;
import game.entity.projectiles.Testprojectiles;
import game.gui.PlayerUI;
import game.gui.Textbox;
import game.input.KeyHandler;
import game.input.MouseHandler;
import game.level.Level;
import game.util.Constants;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameLevel extends GameState {

    protected Player player;
    protected Level botLayer;
    protected Level topLayer;
    protected PlayerUI gui;
    protected Textbox text;
    protected boolean showText;
    protected boolean blockInput = false;

    protected ArrayList<Projectile> proj;

    public GameLevel(GameStateManager gsm) {
        super(gsm);
        init();
        initLists();
    }

    public void init() {

    }

    public void initLists() {
        proj = new ArrayList<Projectile>();
    }

    public void add(Testprojectiles p) {
        proj.add(p);
    }

    public void update() {

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
        for (int i = 0; i < proj.size(); i++) {
            Projectile p = proj.get(i);
            p.update();
            if(p.remove()) {
                proj.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (botLayer != null) botLayer.render(g);

        if (player != null) player.render(g);

        for(int i = 0; i < proj.size(); i++) {
            proj.get(i).render(g);
        }

        if (topLayer != null) topLayer.renderOverlay(g);

        if (gui != null) gui.render(g);

        if (showText) text.render(g);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.tick();


        if(blockInput || player.getHealth() == 0) return;
        if(key.escape.clicked) {
            gsm.pause();
        }
        if(mouse.getButton() == 1) {
            player.shoot(mouse, this);
        }
        player.setUp(key.up.down);
        player.setLeft(key.left.down);
        player.setDown(key.down.down);
        player.setRight(key.right.down);
//        if(key.shift.clicked) showText = !showText;
        player.setShift(key.shift.down);
        if(key.f1.clicked) player.reduceHealth();
        if(key.menu.clicked) gsm.setState(GameStateManager.LEVEL2STATE);

    }

}
