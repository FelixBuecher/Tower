package Game.GameState;

import Game.Audio.JukeBox;
import Game.Input.Key;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import Game.Tools.Constants;

public class MenuState extends GameState {

    private BufferedImage cursor;
    private BufferedImage bg;
    private int choice = 0;
    private final String[] options = {"New Game", "Load", "Option", "Quit"};
    private final int shadow = 2;

    private Font titleFont, font, font2;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        try {
            // Load selection cursor
            cursor = ImageIO.read(getClass().getResourceAsStream("/HUD/Cursor.png"));

            // Setting up fonts
            titleFont = new Font("Times New Roman", Font.PLAIN, Constants.WIDTH / 9);
            font = new Font("Arial", Font.PLAIN, Constants.WIDTH / 18);
            font2 = new Font("Arial", Font.PLAIN, Constants.WIDTH / 42);

            // Loading background
            bg = ImageIO.read(getClass().getResourceAsStream("/HUD/Wall.png"));

            // Loading sfx
            JukeBox.load("/Audio/SFX/menuoption.mp3", "menuoption");
            JukeBox.load("/Audio/SFX/menuselect.mp3", "menuselect");

            // Loading music
            JukeBox.load("/Audio/Music/level1.mp3", "level1");
            JukeBox.loop("level1",  Constants.VOLUME);



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
        int x = Constants.WIDTH / 2 - font.getSize() * 2;
        int y = Constants.HEIGHT / 2;
        int fs = font.getSize();

        // Drawing the background
        g.drawImage(bg.getScaledInstance(Constants.WIDTH, Constants.HEIGHT, 0), 0, 0, null);

        // Drawing the title
        g.setColor(Color.GRAY);
        g.setFont(titleFont);
        g.drawString("T O W E R", Constants.WIDTH / 4 + shadow, Constants.HEIGHT / 4 + shadow);
        g.setColor(Color.WHITE);
        g.drawString("T O W E R", Constants.WIDTH / 4, Constants.HEIGHT / 4);

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
        if(choice == 0) g.drawImage(cursor, x - 32, (int) (y - fs * 0.9), null);
        else if(choice == 1) g.drawImage(cursor, x - 32, (int) (y + fs * 0.1), null);
        else if(choice == 2) g.drawImage(cursor, x - 32, (int) (y + fs * 1.1), null);
        else if(choice == 3) g.drawImage(cursor, x - 32, (int) (y + fs * 2.1), null);

        // Credit
        g.setFont(font2);
        g.setColor(Color.GRAY);
        g.drawString("Felix B. 2021", 10 + 1, Constants.HEIGHT - 10 + 1);
        g.setColor(Color.WHITE);
        g.drawString("Felix B. 2021", 10, Constants.HEIGHT - 10);
    }

    private void select() {
        if(choice == 0) {
            JukeBox.play("menuselect", Constants.VOLUME);
            gsm.setState(GameStateManager.LEVEL1STATE);
        } else if(choice == 1){
            JukeBox.play("menuselect", Constants.VOLUME);
            //PlayerSave.init();
        } else if(choice == 2){
            JukeBox.play("menuselect", Constants.VOLUME);
        } else if(choice == 3) {
            System.exit(0);
        }
    }

    @Override
    public void handleInput() {
        if(Key.isPressed(Key.E_KEY)) select();
        if(Key.isPressed(Key.UP)) {
            if(choice > 0) {
                JukeBox.play("menuoption", Constants.VOLUME);
                choice--;
            }
        }
        if(Key.isPressed(Key.DOWN)) {
            if(choice < options.length - 1) {
                JukeBox.play("menuoption", Constants.VOLUME);
                choice++;
            }
        }
    }
}
