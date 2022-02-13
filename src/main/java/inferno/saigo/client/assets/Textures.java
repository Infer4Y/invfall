package inferno.saigo.client.assets;

import inferno.saigo.common.init.Tiles;
import inferno.saigo.common.items.Item;
import inferno.saigo.common.items.ItemIngot;
import inferno.saigo.common.tiles.Tile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Textures {
    private static final HashMap<String, LinkedList<Texture>> TEXTURE_MAP = new HashMap<>();
    private static Texture placeholder;

    static {
        try {
            LinkedList <Texture> temp = new LinkedList<>();
            temp.add(new Texture(new ResourceLocation("textures/placeholder.png")));
            TEXTURE_MAP.put("placeholder", temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init(){
        try {
            Textures.registerTexture(Tiles.TILE);
            Textures.registerTexture(Tiles.BRICK);
            Textures.registerTexture(Tiles.BLACK_TILE);
            Textures.registerTexture(Tiles.WALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static void registerTexture(Item... items){
        for (Item item: items) {
            try {
                registerTexture(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerTexture(ItemIngot... items){
        for (ItemIngot item: items) {
            try {
                registerTexture(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerTexture(Tile... items){
        for (Tile item: items) {
            try {
                registerTexture(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static LinkedList<Texture> getTexture(String name){
        return TEXTURE_MAP.getOrDefault(name, TEXTURE_MAP.get("placeholder"));
    }
}
