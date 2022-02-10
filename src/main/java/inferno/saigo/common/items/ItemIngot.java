package inferno.saigo.common.items;

import java.awt.*;

public class ItemIngot extends Item {
    private final int color;

    public ItemIngot(String name, int color) {
        super(name);
        this.color = color;
    }

    public ItemIngot(String name) {
        super(name);
        this.color = 0xFFF;
    }

    public Color getColor() {
        return  new Color(color);
    }
}
