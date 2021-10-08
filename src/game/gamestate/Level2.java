package game.gamestate;

import game.audio.JukeBox;
import game.entity.mob.player.Player;
import game.graphics.Sprite;
import game.gui.PlayerUI;
import game.gui.Textbox;
import game.level.Level;
import game.util.Constants;

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
        gui = new PlayerUI(player);
        text = Textbox.createTextbox("This is a test");
        text.setLeft(Sprite.playerSprite.getImage());
        JukeBox.load("level1", "level1", false);
        JukeBox.loop("level1", Constants.v_bgm);
    }
}
