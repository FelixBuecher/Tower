package game.gamestate;

import game.audio.JukeBox;
import game.entity.Mob.Player.Player;
import game.graphics.Sprite;
import game.gui.GUI;
import game.gui.Textbox;
import game.level.Level;

public class Level2 extends GameLevel {

    public Level2(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        botLayer = new Level("level2", tilesize);
//        topLayer = new Level("levelOver", tilesize);
        player = new Player(Sprite.playerSprite, botLayer);
        player.setPosition(50, 50);
        gui = new GUI(player);
        text = Textbox.createTextbox("This is a test");
        text.setLeft(Sprite.playerSprite.getImage());
        JukeBox.load("level1", "level1", false);
        JukeBox.loop("level1", vol);
    }
}
