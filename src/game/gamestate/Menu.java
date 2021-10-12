package game.gamestate;

import game.audio.JukeBox;
import game.input.KeyHandler;
import game.input.MouseHandler;
import game.util.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

import static game.util.Util.loadImage;

/**
 * For now its just the title screen, but I can probably reuse this class
 * and create different useful tools with it, for example an inventory.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Menu extends GameState {

    private Image bg;
    private int choice = 0;
    private final String[] options = {"New Game", "Load", "Option", "Quit"};
    private final int shadow = 2;

    private Font titleFont, font, font2;

    public Menu(GameStateManager gsm) {
        super(gsm);
        try {

            // Setting up fonts
            titleFont = new Font("Times New Roman", Font.PLAIN, width / 9);
            font = new Font("Arial", Font.PLAIN, width / 18);
            font2 = new Font("Arial", Font.PLAIN, width / 42);

            // Loading background
//            bg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Textures/GUI/Wall.png")));
            bg = loadImage("Wall");
            bg = bg.getScaledInstance(width, height, 0);

            // Loading sfx
            JukeBox.load("menuoption", "menuoption", true);
            JukeBox.load("menuselect", "menuselect", true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() {
        JukeBox.load("level1", "level1", false);
        JukeBox.loop("level1",  Constants.v_bgm);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {

        // I am lazy
        int fs = font.getSize();
        int x = hWidth - fs * 2;
        int y = hHeight;

        // Drawing the background
        g.drawImage(bg, null, null);

        // Drawing the title
        g.setColor(Color.GRAY);
        g.setFont(titleFont);
        g.drawString("T O W E R", width / 4 + shadow, height / 4 + shadow);
        g.setColor(Color.WHITE);
        g.drawString("T O W E R", width / 4, height / 4);

        // Draw options
        g.setColor(Color.GRAY);
        g.setFont(font);
        g.drawString(options[0], x + shadow, y + shadow);
        g.drawString(options[1], x + shadow, y + fs + shadow);
        g.drawString(options[2], x + shadow, y + fs * 2 + shadow);
        g.drawString(options[3], x + shadow, y + fs * 3 + shadow);
        g.setColor(Color.WHITE);
        g.drawString(options[0], x, y);
        g.drawString(options[1], x, y + fs);
        g.drawString(options[2], x, y + fs * 2);
        g.drawString(options[3], x, y + fs * 3);


        // Draw selection cursor
        if(choice == 0) g.drawString(">", x - 32, y);
        else if(choice == 1) g.drawString(">", x - 32, y + fs);
        else if(choice == 2) g.drawString(">", x - 32, y + fs * 2);
        else if(choice == 3) g.drawString(">", x - 32, y + fs * 3);

        // Credit
        g.setFont(font2);
        g.setColor(Color.GRAY);
        g.drawString("Felix B. 2021", 10 + 1, height - 10 + 1);
        g.setColor(Color.WHITE);
        g.drawString("Felix B. 2021", 10, height - 10);
    }

    private void select() {
        if(choice == 0) {
            JukeBox.play("menuselect", Constants.v_sfx);
            gsm.setState(GameStateManager.LEVEL1STATE);
        } else if(choice == 1){
            JukeBox.play("menuselect", Constants.v_sfx);
            //PlayerSave.init();
        } else if(choice == 2){
            JukeBox.play("menuselect", Constants.v_sfx);
            gsm.setState(GameStateManager.OPTIONSTATE);
        } else if(choice == 3) {
            System.exit(0);
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        key.down.tick();
        key.up.tick();
        key.menu.tick();

        if(key.menu.clicked){
            select();
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
    }
}
