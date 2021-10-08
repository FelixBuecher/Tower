package game.gui;

import game.util.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Textbox {

    private BufferedImage heart;
    private String text;
    private Image left;
    private Image right;
    private Font font = new Font("Comfortaa", Font.PLAIN, 16);
    private Color windowColor = Color.decode("0x33333333");
    private Color textColor = Color.decode("0xFFFFFF");

    private Textbox(String s) {
        text = s;
    }

    public static Textbox createTextbox(String s) {
        return new Textbox(s);
    }

    public void setText(String s) {
        text = s;
    }

    public void setRight(Image img) {
        right = img.getScaledInstance(80, 80, 0);
    }

    public void setLeft(Image img) {
        left = img.getScaledInstance(80, 80, 0);
    }

    public String getText() {
        return text;
    }

    public void render(Graphics2D g) {
        g.setFont(font);
        g.setColor(windowColor);
        g.fillRect(70, Constants.height -110, Constants.width - 140, 100);
        g.setColor(textColor);
        g.drawString(text, 170, Constants.height - 80);
        if (left != null) g.drawImage(left, 80, Constants.height - 100, null);
        if (right != null) g.drawImage(right, Constants.width - 200, Constants.height - 100, null);

    }
}
