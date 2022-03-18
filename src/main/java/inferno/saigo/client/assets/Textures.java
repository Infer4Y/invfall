package inferno.saigo.client.assets;

import inferno.saigo.common.entities.Entity;
import inferno.saigo.common.init.Entities;
import inferno.saigo.common.init.Items;
import inferno.saigo.common.init.Tiles;
import inferno.saigo.common.items.Item;
import inferno.saigo.common.items.ItemIngot;
import inferno.saigo.common.items.ItemTile;
import inferno.saigo.common.tiles.Tile;

import java.util.HashMap;

public class Textures {
    private static final HashMap<String, Texture> TEXTURE_MAP = new HashMap<>();

    public static void init(){
        {
            TEXTURE_MAP.put("placeholder", new Texture(new ResourceLocation("textures/placeholder.png")));
            TEXTURE_MAP.put("crosshair", new Texture(new ResourceLocation("textures/entities/player.png")));
            TEXTURE_MAP.put("cursor", new Texture(new ResourceLocation("textures/gui/cursor.png")));
        }
        Tiles.getTiles().values().forEach(Textures::registerTexture);
        Items.getItems().values().forEach(Textures::registerTexture);
        Entities.getEntities().values().forEach(Textures::registerTexture);
    }

    public static void registerTexture(Tile tile) {
        TEXTURE_MAP.put(tile.toString(), new Texture(ResourceLoader.receiveImagePath(tile)));
    }

    public static void registerTexture(Item item) {
        if (!(item instanceof ItemTile)) {
            TEXTURE_MAP.put(item.toString(), new Texture(ResourceLoader.receiveImagePath(item)));
        }
    }

    public static void registerTexture(ItemIngot item) {
        TEXTURE_MAP.put(item.toString(), new Texture(ResourceLoader.receiveImagePath(item)));
    }

    public static void registerTexture(Entity entity) {
        TEXTURE_MAP.put(entity.toString(), new Texture(ResourceLoader.receiveImagePath(entity)));
    }

    public static void registerTexture(Item... items){
        for (Item item: items) {
            registerTexture(item);
        }
    }

    public static void registerTexture(ItemIngot... items){
        for (ItemIngot item: items) {
            registerTexture(item);
        }
    }

    public static void registerTexture(Tile... items){
        for (Tile item: items) {
            registerTexture(item);
        }
    }

    public static Texture getTexture(String name){
        return TEXTURE_MAP.getOrDefault(name, TEXTURE_MAP.get("placeholder"));
    }
}
