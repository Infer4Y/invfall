package inferno.saigo.client.rendering;

import java.awt.*;
import java.util.Random;

public class RenderableText extends Renderable {
    private String text;
    private Color color;

    public RenderableText(String text){
        this.text = text;
        this.color = new Color(new Random().nextInt(255*255*255));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(0,0, 64, 64);
        g.setColor(Color.BLACK);
        g.drawRect(0,0, 64, 64);
        g.setColor(Color.WHITE);
        g.drawString(text,5,15);
    }

    @Override
    public void render(Graphics g, int tileSize) {
        g.setColor(color);
        g.fillRect(0,0, tileSize, tileSize);
        g.setColor(Color.BLACK);
        g.drawRect(0,0, tileSize, tileSize);
        g.setColor(Color.WHITE);
        g.drawString(text,4,16);
    }
}
