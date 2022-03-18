package inferno.saigo.client.utils.client;

import inferno.saigo.client.utils.display.DisplayReference;

import java.awt.event.*;

public class MouseController implements MouseListener, MouseMotionListener, MouseWheelListener {
    public boolean mouseLeftDown, rightMouseDown;

    public int currentX, currentY, lastX, lastY;
    public double mouseRotationFromCenter;

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
        if (e.getButton() == MouseEvent.BUTTON1) mouseLeftDown = true;
        if (e.getButton() == MouseEvent.BUTTON3) rightMouseDown = true;
        System.out.println("Mouse pressed"
                + e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) mouseLeftDown = false;
        if (e.getButton() == MouseEvent.BUTTON3) rightMouseDown = false;
        System.out.println("Mouse released"
                + e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        lastX = currentX;
        lastY = currentY;
        currentX = e.getX();
        currentY = e.getY();
        double dx = currentX - ((float) DisplayReference.view.getWidth())/DisplayReference.viewScale/2.0;
        double dy = currentY - ((float) DisplayReference.view.getHeight())/DisplayReference.viewScale/2.0;
        mouseRotationFromCenter = Math.atan2(dy, dx) - Math.PI / 2;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastX = currentX;
        lastY = currentY;
        currentX = e.getX();
        currentY = e.getY();
        double dx = currentX - ((float) DisplayReference.view.getWidth())/DisplayReference.viewScale/2.0;
        double dy = currentY - ((float) DisplayReference.view.getHeight())/DisplayReference.viewScale/2.0;
        mouseRotationFromCenter = Math.atan2(dy, dx) - Math.PI / 2;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //e.getScrollAmount();
    }
}
