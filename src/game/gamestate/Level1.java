package game.gamestate;

import game.audio.JukeBox;
import game.entity.Mob.Player.Player;
import game.gui.GUI;
import game.graphics.Sprite;
import game.gui.Textbox;
import game.level.Level;

/**
 * The first level of the game, for now its just a test level
 * but I will use this class to extend and create more levels with it.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Level1 extends GameLevel {

    public Level1(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        botLayer = new Level("level", tilesize);
//        topLayer = new Level("level2", tilesize);
        player = new Player(Sprite.playerSprite, botLayer);
        player.setPosition(50, 50);
        gui = new GUI(player);
        text = Textbox.createTextbox("This is a test");
        text.setLeft(Sprite.playerSprite.getImage());
        JukeBox.load("level1", "level1", false);
        JukeBox.loop("level1", vol);
    }

}
