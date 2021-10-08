package game.input;

import game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Handler for mouse input.
 *
 * @author Felix Buecher
 * @version 1.0
 */
public class MouseHandler implements MouseListener, MouseMotionListener {

    private static int mouseX = -1, mouseY = -1, mouseB = -1;

    public MouseHandler(Game game) {
        game.addMouseListener(this);
        game.addMouseMotionListener(this);
    }

    public int getX() {
        return mouseX;
    }

    public int getY() {
        return mouseY;
    }

    public int getButton() {
        return mouseB;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
