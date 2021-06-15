package Game.GameState;

import Game.Audio.JukeBox;
import Game.Input.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {

    private BufferedImage head;
    private int choice = 0;
    private String[] options = {"New Game", "Load", "Option", "Quit"};

    private Font titleFont, font, font2;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        try {
            // Load selection cursor
            head = ImageIO.read(getClass().getResourceAsStream("/HUD/Hud.gif")).getSubimage(0, 12, 12, 11);

            // Setting up fonts
            titleFont = new Font("Times New Roman", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 14);
            font2 = new Font("Arial", Font.PLAIN, 10);

            // Loading sfx
            JukeBox.load("/Audio/SFX/menuoption.mp3", "menuoption");
            JukeBox.load("/Audio/SFX/menuselect.mp3", "menuselect");

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
        // Drawing the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        // Drawing the title
        g.setColor(Color.CYAN);
        g.setFont(titleFont);
        g.drawString("T O W E R", Game.WIDTH / 3, Game.HEIGHT / 4);

        // Draw options
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(options[0], Game.WIDTH / 3, Game.HEIGHT / 3 - 20);
        g.drawString(options[1], Game.WIDTH / 3, Game.HEIGHT / 3 - 5);
        g.drawString(options[2], Game.WIDTH / 3, Game.HEIGHT / 3 + 10);
        g.drawString(options[3], Game.WIDTH / 3, Game.HEIGHT / 3 + 25);

        // Draw selection cursor
        if(choice == 0) g.drawImage(head, Game.WIDTH / 3 - 25, Game.HEIGHT / 3 - 20, null);
        else if(choice == 1) g.drawImage(head, Game.WIDTH / 3 - 25, Game.HEIGHT / 3 - 5, null);
        else if(choice == 2) g.drawImage(head, Game.WIDTH / 3 - 25, Game.HEIGHT / 3 + 10, null);
        else if(choice == 3) g.drawImage(head, Game.WIDTH / 3 - 25, Game.HEIGHT / 3 + 25, null);

        // Credit
        g.setFont(font2);
        g.drawString("Felix B. 2021", 10, Game.HEIGHT - 10);
    }

    private void select() {
        if(choice == 0) {
            JukeBox.play("menuselect", 30);
            gsm.setState(GameStateManager.LEVEL1STATE);
        } else if(choice == 1){
            JukeBox.play("menuselect", 30);
            PlayerSave.init();
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
