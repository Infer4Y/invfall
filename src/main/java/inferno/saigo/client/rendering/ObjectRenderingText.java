package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.Fonts;

import java.awt.*;
import java.util.Random;

public class ObjectRenderingText extends ObjectRendering {
    private String text;
    private int x, y;

    public ObjectRenderingText(String text, int x, int y){
        this.text = text;
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        graphics.setColor(new Color(111, 11, 145, 199));
        graphics.setFont(Fonts.OPEN_SANS);
        graphics.drawString(text,getX(),getY());
        graphics.setColor(Color.WHITE);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
