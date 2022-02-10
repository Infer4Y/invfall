package inferno.saigo.client.rendering;

import inferno.saigo.client.assets.Textures;
import inferno.saigo.client.utils.display.DisplayReference;
import inferno.saigo.common.init.Tiles;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Renderer {
    // Int : Layer = 3
    private final HashMap<Integer, LinkedList<ObjectRendering>> render_object_list_list = new HashMap<>();

    public Camera camera = new Camera(150,150);


    public int gridSpaceX = 5;
    //This is the size per tile in pixels. One length is equivalent in pixel
    public int tileSize = 128;
    //This is the size per tile in pixels. One length is equivalent in pixel
    public int tileRenderDistance = 4;

    public Renderer() {
        //Background Layer
        render_object_list_list.put(0, new LinkedList<>());
        //Wall/Player Layer
        render_object_list_list.put(1, new LinkedList<>());
        //Foreground Layer
        render_object_list_list.put(2, new LinkedList<>());

        try {
            Textures.registerTexture(Tiles.TILE);
            Textures.registerTexture(Tiles.BRICK);
            Textures.registerTexture(Tiles.BLACK_TILE);
            Textures.registerTexture(Tiles.WALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics2D graphics){
        render_object_list_list.values().forEach((object_to_render_list) -> object_to_render_list.forEach(object_to_render -> {
            if (object_to_render instanceof ObjectRenderingCoord) {
                if (((ObjectRenderingCoord) object_to_render).getX() > camera.getX() - tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render).getX() < camera.getX() + tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render).getY() > camera.getY() - tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render).getY() < camera.getY() + tileRenderDistance) {

                    graphics.translate((int)((-camera.getX() * tileSize) + (DisplayReference.view.getWidth() >> 1)),
                            (int)((-camera.getY() * tileSize) + (DisplayReference.view.getHeight() >> 1)));
                    object_to_render.render(graphics, tileSize);
                    graphics.translate((int)((camera.getX() * tileSize) - (DisplayReference.view.getWidth() >> 1)),
                            (int)((camera.getY() * tileSize) - (DisplayReference.view.getHeight() >> 1)));
                }
            } else {
                graphics.translate((int) (-camera.getX() * tileSize),
                        (int) (-camera.getY() * tileSize));
                object_to_render.render(graphics, tileSize);
                graphics.translate((int)(camera.getX() * tileSize), (int)(camera.getY() * tileSize));
            }
        }));
    }


    public void add(int layer, ObjectRendering objectRendering) {
        render_object_list_list.get(layer).add(objectRendering);
    }

    public void clear() {
        render_object_list_list.clear();
        render_object_list_list.put(0, new LinkedList<>());
        render_object_list_list.put(1, new LinkedList<>());
        render_object_list_list.put(2, new LinkedList<>());
    }
}
