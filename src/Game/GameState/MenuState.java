package Game.GameState;

import Game.Audio.JukeBox;
import Game.Input.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import Game.Game;

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
            titleFont = new Font("Times New Roman", Font.PLAIN, Game.WIDTH / 9);
            font = new Font("Arial", Font.PLAIN, Game.WIDTH / 18);
            font2 = new Font("Arial", Font.PLAIN, Game.WIDTH / 42);

            // Loading sfx
            JukeBox.load("/Audio/SFX/menuoption.mp3", "menuoption");
            JukeBox.load("/Audio/SFX/menuselect.mp3", "menuselect");

            // Loading background?
            bg = ImageIO.read(getClass().getResourceAsStream("/HUD/Wall.png"));

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
        int x = Game.WIDTH / 2 - font.getSize() * 2;
        int y = Game.HEIGHT / 2;
        int fs = font.getSize();

        // Drawing the background
        g.drawImage(bg.getScaledInstance(Game.WIDTH, Game.HEIGHT, 0), 0, 0, null);

        // Drawing the title
        g.setColor(Color.GRAY);
        g.setFont(titleFont);
        g.drawString("T O W E R", Game.WIDTH / 4 + shadow, Game.HEIGHT / 4 + shadow);
        g.setColor(Color.WHITE);
        g.drawString("T O W E R", Game.WIDTH / 4, Game.HEIGHT / 4);

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
        g.drawString("Felix B. 2021", 10 + 1, Game.HEIGHT - 10 + 1);
        g.setColor(Color.WHITE);
        g.drawString("Felix B. 2021", 10, Game.HEIGHT - 10);
    }

    private void select() {
        if(choice == 0) {
            JukeBox.play("menuselect", 30);
            gsm.setState(GameStateManager.LEVEL1STATE);
        } else if(choice == 1){
            JukeBox.play("menuselect", 30);
            //PlayerSave.init();
        } else if(choice == 2){
            JukeBox.play("menuselect", 30);
        } else if(choice == 3) {
            System.exit(0);
        }
    }

    @Override
    public void handleInput() {
        if(Key.isPressed(Key.E_KEY)) select();
        if(Key.isPressed(Key.UP)) {
            if(choice > 0) {
                JukeBox.play("menuoption", 30);
                choice--;
            }
        }
        if(Key.isPressed(Key.DOWN)) {
            if(choice < options.length - 1) {
                JukeBox.play("menuoption", 30);
                choice++;
            }
        }
    }
}
