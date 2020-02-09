package inferno.saigo.client.rendering;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Renderer {
    // Int : Layer = 3
    private HashMap<Integer, LinkedList<Renderable>> renderlist = new HashMap<>();

    public Camera camera = new Camera(150,150);

    public int gridspaceX = 5;
    public int tileSize = 16;

    public Renderer() {
        renderlist.put(0, new LinkedList<Renderable>());
        renderlist.put(1, new LinkedList<Renderable>());
        renderlist.put(2, new LinkedList<Renderable>());
    }

    public void render(Graphics g){
        for (int i = 0; i < 3; i++) {
            for (Renderable r : renderlist.get(i)) {
                if (r instanceof RenderableCoord) {
                    if (((RenderableCoord) r).getX() > camera.getX() - 12 && ((RenderableCoord) r).getX() < camera.getX() + 12 &&
                            ((RenderableCoord) r).getY() > camera.getY() - 12 && ((RenderableCoord) r).getY() < camera.getY() + 12) {

                        g.translate((int) (-camera.getX() * tileSize), (int) (-camera.getY() * tileSize));
                        r.render(g, tileSize);
                        g.translate((int) (camera.getX() * tileSize), (int) (camera.getY() * tileSize));
                    }
                } else {
                    g.translate((int) (-camera.getX() * tileSize), (int) (-camera.getY() * tileSize));
                    r.render(g, tileSize);
                    g.translate((int) (camera.getX() * tileSize), (int) (camera.getY() * tileSize));
                }
            }
        }
    }


    public void add(int layer, Renderable renderable) {
        renderlist.get(layer).add(renderable);
    }

    public void clear() {
        renderlist.clear();
        renderlist.put(0, new LinkedList<Renderable>());
        renderlist.put(1, new LinkedList<Renderable>());
        renderlist.put(2, new LinkedList<Renderable>());
    }
}
