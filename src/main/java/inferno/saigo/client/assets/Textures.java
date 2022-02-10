package inferno.saigo.client.assets;

import inferno.saigo.common.items.Item;
import inferno.saigo.common.items.ItemIngot;
import inferno.saigo.common.tiles.Tile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Textures {
    private static final HashMap<String, LinkedList<Texture>> TEXTURE_MAP = new HashMap<>();

    public static void registerTexture(Tile tile) throws IOException {
        LinkedList <Texture> temp = new LinkedList<>();
        temp.add(new Texture(new ResourceLocation("textures/tiles/"+tile.getName()+".png")));
        TEXTURE_MAP.put(tile.getName(), temp);
    }

    public static void registerTexture(Item item) throws IOException {
        LinkedList <Texture> temp = new LinkedList<>();
        temp.add(new Texture(new ResourceLocation("textures/tiles/"+item.getName()+".png")));
        TEXTURE_MAP.put(item.getName(), temp);
    }

    public static void registerTexture(ItemIngot item) throws IOException {
        LinkedList <Texture> temp = new LinkedList<>();
        temp.add(new Texture(new ResourceLocation("textures/tiles/"+item.getName()+".png")));
        TEXTURE_MAP.put(item.getName(), temp);
    }

    public static LinkedList<Texture> getTexture(String name){
        return TEXTURE_MAP.getOrDefault(name, null);
    }
}
