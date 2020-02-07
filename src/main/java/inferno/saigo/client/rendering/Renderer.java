package inferno.saigo.client.rendering;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Renderer {
    // Int : Layer = 3
    private HashMap<Integer, LinkedList<Renderable>> renderlist = new HashMap<>();

    int gridspace = 5;
    int x = 0, y = 0;

    public Renderer() {
        renderlist.put(0, new LinkedList<Renderable>());
        renderlist.put(1, new LinkedList<Renderable>());
        renderlist.put(2, new LinkedList<Renderable>());
    }

    public void render(Graphics g){
        for (int i = 0; i < 3; i++) {
            for (Renderable r : renderlist.get(i)) {
                g.translate(x*64, y*64);
                r.render(g);
                g.translate(-x*64, -y*64);

                if (x == gridspace){
                    x = 0;
                    y ++;
                } else {
                    x++;
                }
            }
            x = y = 0;
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
