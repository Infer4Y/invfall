package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.collections.Fonts;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;

public class ObjectRenderingText extends ObjectRendering {
    private String text;
    private int x, y, width, height;
    private Font font;
    public boolean shouldRender;

    public ObjectRenderingText(String text, int x, int y){
        this(text, Fonts.AUDIO_WIDE, x, y);
    }

    public ObjectRenderingText(String text, Font font, int x, int y){
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        if ( !shouldRender ) return;
        graphics.setColor(new Color(0,0,0, 128));
        Font backUp = graphics.getFont();

        graphics.setFont(getFont());

        FontRenderContext context = graphics.getFontRenderContext();

        int textWidth = getTextWidth(graphics, context);

        LineMetrics ln = getFont().getLineMetrics(text, context);

        int textHeight = (int) (ln.getAscent() + ln.getDescent());

        graphics.fillRoundRect(getX()-4,getY()-textHeight/2-4, textWidth+16, (int) (textHeight*(1.0 + .125f/2)),10,10);

        graphics.setColor(new Color(230, 163, 255));

        graphics.drawString(text,getX()+4,getY()+6);

        graphics.setColor(Color.BLACK);

        graphics.setFont(backUp);

        width = textWidth;
        height = textHeight;
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

    public int getTextWidth(Graphics2D graphics, FontRenderContext context){
        return (int) getFont().getStringBounds(text, context).getWidth();
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
