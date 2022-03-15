package inferno.saigo.client.utils.client;

import java.awt.event.*;

public class MouseController implements MouseListener, MouseMotionListener, MouseWheelListener {
    public boolean mouseLeftDown, rightMouseDown;

    public int currentX, currentY, lastX, lastY;

    public boolean isMouseLeftDown() {
        return mouseLeftDown;
    }

    public boolean isRightMouseDown() {
        return rightMouseDown;
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        boolean b1 =  (e.getButton() == MouseEvent.BUTTON1) ? (mouseLeftDown = true) : (mouseLeftDown = false);
        boolean b = (e.getButton() == MouseEvent.BUTTON2) ? (rightMouseDown = true) : (rightMouseDown = false);
        System.out.println("Mouse pressed"
                + e.getButton() + b + b1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        boolean b1 =  (e.getButton() == MouseEvent.BUTTON1) ? (mouseLeftDown = false) : (mouseLeftDown = true);
        boolean b = (e.getButton() == MouseEvent.BUTTON2) ? (rightMouseDown = false) : (rightMouseDown = true);
        System.out.println("Mouse released"
                + e.getButton() + b + b1);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastX = currentX;
        lastY = currentY;
        currentX = e.getX();
        currentY = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //e.getScrollAmount();
    }
}
