package inferno.saigo.client.rendering;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Renderer {
    // Int : Layer = 3
    private final HashMap<Integer, LinkedList<Renderable>> renderlist = new HashMap<>();

    public Camera camera = new Camera(150,150);

    public int gridSpaceX = 5;
    public int tileSize = 16;

    public Renderer() {
        renderlist.put(0, new LinkedList<>());
        renderlist.put(1, new LinkedList<>());
        renderlist.put(2, new LinkedList<>());
    }

    public void render(Graphics graphics){
        renderlist.values().forEach((object_to_render_list) -> object_to_render_list.forEach(object_to_render -> {
            if (object_to_render instanceof ObjectRenderingCoord) {
                if (((ObjectRenderingCoord) object_to_render).getX() > camera.getX() - 12
                        && ((ObjectRenderingCoord) object_to_render).getX() < camera.getX() + 12
                        && ((ObjectRenderingCoord) object_to_render).getY() > camera.getY() - 12
                        && ((ObjectRenderingCoord) object_to_render).getY() < camera.getY() + 12) {

                    graphics.translate((int) (-camera.getX() * tileSize),
                            (int) (-camera.getY() * tileSize));
                    object_to_render.render(graphics, tileSize);
                    graphics.translate((int) (camera.getX() * tileSize),
                            (int) (camera.getY() * tileSize));
                }
            } else {
                graphics.translate((int) (-camera.getX() * tileSize),
                        (int) (-camera.getY() * tileSize));
                object_to_render.render(graphics, tileSize);
                graphics.translate((int) (camera.getX() * tileSize),
                        (int) (camera.getY() * tileSize));
            }
        }));
    }


    public void add(int layer, Renderable renderable) {
        renderlist.get(layer).add(renderable);
    }

    public void clear() {
        renderlist.clear();
        renderlist.put(0, new LinkedList<>());
        renderlist.put(1, new LinkedList<>());
        renderlist.put(2, new LinkedList<>());
    }
}
