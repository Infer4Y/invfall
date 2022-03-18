package inferno.saigo.client.rendering;

import inferno.saigo.client.utils.display.DisplayReference;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Renderer {
    // Int : Layer = 3
    private final HashMap<Integer, LinkedList<ObjectRendering>> objectRenderings = new HashMap<>();

    public Camera camera = new Camera(0,0);


    //public int gridSpaceX = 5;
    //This is the size per tile in pixels. One length is equivalent in pixel
    public int tileSize = 64;
    //This is the size per tile in pixels. One length is equivalent in pixel
    public int tileRenderDistance = 4;
    //This it to enable and disable rendering.
    public boolean renderEnabled = false;

    public Renderer() {
        //Background Layer
        objectRenderings.put(0, new LinkedList<>());
        //Wall/Player Layer
        objectRenderings.put(1, new LinkedList<>());
        //Foreground Layer
        objectRenderings.put(2, new LinkedList<>());
    }

    public void render(Graphics2D graphics){
        if (!renderEnabled) return;

        float locationX = camera.getX(), locationY = camera.getY();

        objectRenderings.values().forEach((object_to_render_list) -> object_to_render_list.forEach(object_to_render -> {
            if (object_to_render instanceof ObjectRenderingCoord) {
                if (((ObjectRenderingCoord) object_to_render).getY() > locationX - tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render).getY() < locationX + tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render).getX() > locationY - tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render).getX() < locationY + tileRenderDistance) {

                    graphics.translate((int)((-locationX * tileSize) + (DisplayReference.view.getWidth() >> 1)),
                            (int)((-locationY * tileSize) + (DisplayReference.view.getHeight() >> 1)));
                    object_to_render.render(graphics, tileSize);
                    graphics.translate((int)((locationX * tileSize) - (DisplayReference.view.getWidth() >> 1)),
                            (int)((locationY * tileSize) - (DisplayReference.view.getHeight() >> 1)));
                }
            } else {
                //graphics.translate((int) (-camera.getX() * tileSize), (int) (-camera.getY() * tileSize));
                object_to_render.render(graphics, tileSize);
                //graphics.translate((int)(camera.getX() * tileSize), (int)(camera.getY() * tileSize));
            }
        }));
    }



    public void add(int layer, ObjectRendering objectRendering) {
        objectRenderings.get(layer).add(objectRendering);
    }

    public void clear() {
        objectRenderings.clear();
        objectRenderings.put(0, new LinkedList<>());
        objectRenderings.put(1, new LinkedList<>());
        objectRenderings.put(2, new LinkedList<>());
    }
}
