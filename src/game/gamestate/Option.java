package game.gamestate;

import game.audio.JukeBox;
import game.input.KeyHandler;
import game.input.MouseHandler;
import game.util.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Option extends GameState {

    private int choice = 0;
    private final String[] options = {"SFX", "BGM", "Enable Hitbox", "Back"};

    int fs;
    int x;
    int y = (int) (hHeight / 1.25);
    DecimalFormat df = new DecimalFormat("#.#");


    private Font font;

    public Option(GameStateManager gsm) {
        super(gsm);

        try {

            // Setting up fonts
            font = new Font("Arial", Font.PLAIN, width / 34);
            fs = font.getSize();
            x = hWidth - fs * 5;
            // Loading sfx
            JukeBox.load("menuoption", "menuoption", true);
            JukeBox.load("menuselect", "menuselect", true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(options[0], x, y);
        g.drawString(df.format((30 - (double) Constants.v_sfx) / 30 * 100) + "%", x + 250, y);
        g.drawString(options[1], x, y + fs);
        g.drawString(df.format((30 - (double) Constants.v_bgm) / 30 * 100) + "%", x + 250, y + fs);
        g.drawString(options[2], x, y + fs * 2);
        g.drawString(String.valueOf(Constants.drawHitBox), x + 250, y + fs * 2);
        g.drawString(options[3], x, y + fs * 3);

        // Draw selection cursor
        if(choice == 0) g.drawString(">", x - 32, y);
        else if(choice == 1) g.drawString(">", x - 32, y + fs);
        else if(choice == 2) g.drawString(">", x - 32, y + fs * 2);
        else if(choice == 3) g.drawString(">", x - 32, y + fs * 3);

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.tick();

        if(key.menu.clicked && choice == 3){
            JukeBox.play("menuoption", Constants.v_sfx);
            gsm.setState(GameStateManager.MENUSTATE);
        }

        if(key.up.clicked) {
            if(choice > 0) {
                JukeBox.play("menuoption", Constants.v_sfx);
                choice--;
            }
        }

        if(key.down.clicked) {
            if(choice < options.length - 1) {
                JukeBox.play("menuoption", Constants.v_sfx);
                choice++;
            }
        }

        if(key.left.clicked) {
            if(choice == 0) {
                if(Constants.v_sfx >= 0 && Constants.v_sfx < 30) Constants.v_sfx += 1;
                JukeBox.play("menuoption", Constants.v_sfx);
            }
            if(choice == 1) {
                if(Constants.v_bgm >= 0 && Constants.v_bgm < 30) Constants.v_bgm += 1;
                JukeBox.changeVolume("level1", Constants.v_bgm);
                JukeBox.play("menuoption", Constants.v_sfx);
            }
            if(choice == 2) {
                Constants.drawHitBox = false;
                JukeBox.play("menuoption", Constants.v_sfx);
            }
        }

        if(key.right.clicked) {
            if(choice == 0) {
                if(Constants.v_sfx > 0 && Constants.v_sfx <= 30) Constants.v_sfx -= 1;
                JukeBox.play("menuoption", Constants.v_sfx);
            }
            if(choice == 1) {
                if(Constants.v_bgm > 0 && Constants.v_bgm <= 30) Constants.v_bgm -= 1;
                JukeBox.changeVolume("level1", Constants.v_bgm);
                JukeBox.play("menuoption", Constants.v_sfx);
            }
            if(choice == 2) {
                Constants.drawHitBox = true;
                JukeBox.play("menuoption", Constants.v_sfx);
            }
        }
    }
}
