package inferno.saigo.client.rendering;

import inferno.saigo.client.utils.client.Pair;
import inferno.saigo.client.utils.display.DisplayReference;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Renderer {
    // Int : Layer = 3
    private final ConcurrentHashMap<Integer, ArrayList<Pair<UUID, ObjectRendering>>> objectRenderings = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, ArrayList<Pair<UUID, ObjectRendering>>> objectPoolRenderings = new ConcurrentHashMap<>();
    private final HashMap<Integer, ArrayList<Pair<UUID, ObjectRendering>>> filteredPoolRenderings = new HashMap<>();

    public Camera camera = new Camera(0,0);


    //public int gridSpaceX = 5;
    //This is the size per tile in pixels. One length is equivalent in pixel
    public int tileSize = 64;
    //This is the size per tile in pixels. One length is equivalent in pixel
    public int tileRenderDistance = 8;
    //This it to enable and disable rendering.
    public boolean renderEnabled = false;

    public Renderer() {
        for (int layer = 0; layer < 3; layer++) {
            objectRenderings.put(layer, new ArrayList<>());
            objectPoolRenderings.put(layer, new ArrayList<>());
            filteredPoolRenderings.put(layer, new ArrayList<>());
        }
    }

    public void render(Graphics2D graphics){
        if (!renderEnabled) return;
        objectPoolRenderings.forEach((layer, list) -> list.forEach(object -> add(layer, object.value)));
        clearPool();

        float locationX = camera.getX(), locationY = camera.getY();

        objectRenderings.values().forEach((object_to_render_list) -> object_to_render_list.forEach(object_to_render -> {
            if (object_to_render.value instanceof ObjectRenderingCoord) {
                /*if (((ObjectRenderingCoord) object_to_render.value).getY() > locationX - tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render.value).getY() < locationX + tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render.value).getX() > locationY - tileRenderDistance
                        && ((ObjectRenderingCoord) object_to_render.value).getX() < locationY + tileRenderDistance) {*/

                    graphics.translate((int)((-locationX * tileSize) + (DisplayReference.view.getWidth() >> 1)),
                            (int)((-locationY * tileSize) + (DisplayReference.view.getHeight() >> 1)));
                    object_to_render.value.render(graphics, tileSize);
                    graphics.translate((int)((locationX * tileSize) - (DisplayReference.view.getWidth() >> 1)),
                            (int)((locationY * tileSize) - (DisplayReference.view.getHeight() >> 1)));
                //}
            } else {
                //graphics.translate((int) (-camera.getX() * tileSize), (int) (-camera.getY() * tileSize));
                object_to_render.value.render(graphics, tileSize);
                //graphics.translate((int)(camera.getX() * tileSize), (int)(camera.getY() * tileSize));
            }
        }));

        objectRenderings.forEach((in, val) -> filteredPoolRenderings.put(in, (ArrayList<Pair<UUID, ObjectRendering>>) val.stream().filter((pair -> !pair.value.remove)).collect(Collectors.toList())));

        objectRenderings.putAll(filteredPoolRenderings);

        clearFilter();
    }

    public synchronized void add(int layer, ObjectRendering objectRendering) {
        objectRenderings.get(layer).add(new Pair<>(objectRendering.uuid, objectRendering));
    }

    public void clearPool() {
        objectPoolRenderings.clear();
        for (int layer = 0; layer < 3; layer++) {
            objectPoolRenderings.put(layer, new ArrayList<>());
        }
    }

    public void clearFilter() {
        filteredPoolRenderings.clear();
        for (int layer = 0; layer < 3; layer++) {
            filteredPoolRenderings.put(layer, new ArrayList<>());
        }
    }

    public void addPool(int layer, ObjectRendering objectRendering) {
        System.out.println(objectRendering);
        objectPoolRenderings.get(layer).add(new Pair<>(objectRendering.uuid, objectRendering));
    }
}
