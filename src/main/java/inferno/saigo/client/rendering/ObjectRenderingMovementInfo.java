package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.collections.Fonts;
import inferno.saigo.client.utils.client.ClientUtils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;

public class ObjectRenderingMovementInfo extends ObjectRendering {
    private String[] text;
    private int x, y, width, height;
    private Font font;
    public boolean shouldRender;

    public ObjectRenderingMovementInfo(int x, int y, String... text){
        this(Fonts.AUDIO_WIDE, x, y, text);
    }

    public ObjectRenderingMovementInfo(Font font, int x, int y, String... text){
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics2D graphics, int tileSize) {
        if ( !shouldRender ) return;

        graphics.setFont(font);

        int row = 0;

        float progress = ClientUtils.getMovementProgress();

        FontRenderContext context = graphics.getFontRenderContext();
        LineMetrics ln;
        int textWidth, textHeight = 0, largestWidth = 0, totalHeight = 0;

        for (String textile : text) {

            textWidth = (int) font.getStringBounds(textile, context).getWidth();
            largestWidth = Math.max(largestWidth, textWidth);

            ln = Fonts.AUDIO_WIDE.getLineMetrics(textile, context);

            textHeight = (int) (ln.getAscent() + ln.getDescent());

            totalHeight += textHeight;
        }

        graphics.setColor(new Color(0,0,0, (int)(-128*progress + 128)));

        graphics.fillRoundRect(getX()-4,getY()-textHeight/2-8, largestWidth+16, totalHeight+8,10,10);

        for (String textile : text) {

            ln = font.getLineMetrics(textile, context);

            textHeight = (int) (ln.getAscent() + ln.getDescent());

            graphics.setColor(new Color(230, 163, 255, (int)((-0xFE*progress) + 0xFF)));

            graphics.drawString(textile,getX()+4,getY()+4 + (textHeight * row+ 4));

            row++;
        }

        graphics.setColor(Color.BLACK);
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

    public String[] getText() {
        return text;
    }

    public void setText(String... text) {
        this.text = text;
    }
}
