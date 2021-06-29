package game.gamestate;

import game.audio.JukeBox;
import game.input.Key;
import java.awt.*;
import java.awt.image.BufferedImage;

import static game.tools.Util.loadImage;

/**
 * For now its just the title screen, but I can probably reuse this class
 * and create different useful tools with it, for example an inventory.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class Menu extends GameState {

    private BufferedImage cursor;
    private Image bg;
    private int choice = 0;
    private final String[] options = {"New Game", "Load", "Option", "Quit"};
    private final int shadow = 2;

    private Font titleFont, font, font2;

    public Menu(GameStateManager gsm) {
        super(gsm);
        try {
            // Load selection cursor
            cursor = loadImage("Cursor");

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

            // Loading music
            JukeBox.load("level1", "level1", false);
            JukeBox.loop("level1",  vol);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        handleInput();
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
        if(choice == 0) g.drawImage(cursor, x - 32, (int) (y - fs * 0.7), null);
        else if(choice == 1) g.drawImage(cursor, x - 32, (int) (y + fs * 0.25), null);
        else if(choice == 2) g.drawImage(cursor, x - 32, (int) (y + fs * 1.25), null);
        else if(choice == 3) g.drawImage(cursor, x - 32, (int) (y + fs * 2.25), null);

        // Credit
        g.setFont(font2);
        g.setColor(Color.GRAY);
        g.drawString("Felix B. 2021", 10 + 1, height - 10 + 1);
        g.setColor(Color.WHITE);
        g.drawString("Felix B. 2021", 10, height - 10);
    }

    private void select() {
        if(choice == 0) {
            JukeBox.play("menuselect", vol);
            gsm.setState(GameStateManager.LEVEL1STATE);
        } else if(choice == 1){
            JukeBox.play("menuselect", vol);
            //PlayerSave.init();
        } else if(choice == 2){
            JukeBox.play("menuselect", vol);
        } else if(choice == 3) {
            System.exit(0);
        }
    }

    @Override
    public void handleInput() {
        if(Key.isPressed(Key.E_KEY)) select();
        if(Key.isPressed(Key.UP)) {
            if(choice > 0) {
                JukeBox.play("menuoption", vol);
                choice--;
            }
        }
        if(Key.isPressed(Key.DOWN)) {
            if(choice < options.length - 1) {
                JukeBox.play("menuoption", vol);
                choice++;
            }
        }
    }
}
