package inferno.saigo.client.rendering;

import java.awt.*;
import java.util.Random;

public class ObjectRenderingText extends ObjectRenderingCoord {
    private final String text;
    private final Color color;

    public ObjectRenderingText(String text, int x, int y){
        super(x,y);
        this.text = text;
        this.color = new Color(new Random().nextInt(255*255*255));
    }

    @Override
    public void render(Graphics graphics, int tileSize) {
        graphics.translate(getX() * tileSize, getY() * tileSize);
        graphics.setColor(color);
        graphics.fillRect(0,0, tileSize, tileSize);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0,0, tileSize, tileSize);
        graphics.setColor(Color.WHITE);
        graphics.drawString(text,4,16);
        graphics.translate(-getX() * tileSize, -getY() * tileSize);
    }
}
