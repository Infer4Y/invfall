package inferno.saigo.client.rendering;

import java.awt.*;

public class RenderableText extends Renderable {
    private String text;

    public RenderableText(String text){
        this.text = text;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color((int) text.charAt(0)+(int) text.charAt(1) + (int) text.charAt(2)));
        g.fillRect(0,0, 64, 64);
        g.setColor(Color.BLACK);
        g.drawRect(0,0, 64, 64);
        g.setColor(Color.WHITE);
        g.drawString(text,5,15);
    }
}
