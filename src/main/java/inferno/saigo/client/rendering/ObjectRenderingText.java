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
    public void render(Graphics g, int tileSize) {
        g.translate(getX() * tileSize, getY() * tileSize);
        g.setColor(color);
        g.fillRect(0,0, tileSize, tileSize);
        g.setColor(Color.BLACK);
        g.drawRect(0,0, tileSize, tileSize);
        g.setColor(Color.WHITE);
        g.drawString(text,4,16);
        g.translate(-getX() * tileSize, -getY() * tileSize);
    }
}
