package inferno.saigo.client.rendering;

import java.awt.*;
import java.util.UUID;

public abstract class ObjectRendering {
    public UUID uuid = UUID.randomUUID();
    public abstract void render(Graphics2D graphics, int tileSize);

    public boolean remove;
}
