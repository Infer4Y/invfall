package inferno.saigo.client.assets;

import com.google.gson.Gson;
import inferno.saigo.client.Main;
import inferno.saigo.common.entities.Entity;
import inferno.saigo.common.items.Item;
import inferno.saigo.common.tiles.Tile;

import java.io.*;
import java.util.Objects;

public class ResourceLoader {
    private static final Gson parser = new Gson();


    public static Model item_model = new Model();
    public static Model tile_model = new Model();
    public static Model sound_model = new Model();
    public static Model entity_model = new Model();

    public static ResourceLocation receiveImagePath(Item item){
        ResourceLocation item_resource_location = new ResourceLocation(item.getDomain(), "models/items/" + item.getRegistryName() + ".json");
        try {
            Reader item_reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(item_resource_location.toString())));
            item_model = parser.fromJson(item_reader, Model.class);
        } catch (NullPointerException e){
            System.out.println(item_resource_location +" could not be loaded or is null");
            return null;
        }
        item_resource_location = new ResourceLocation(item_model.domain, "textures/" + item_model.path);

        return item_resource_location;
    }

    public static ResourceLocation receiveImagePath(Tile tile){
        ResourceLocation tile_resource_location = new ResourceLocation(tile.getDomain(), "models/tiles/" + tile.getRegistryName() + ".json");

        try {
            Reader tile_reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(tile_resource_location.toString())));
            tile_model = parser.fromJson(tile_reader, Model.class);
        } catch (NullPointerException e){
            System.out.println(tile_resource_location +" could not be loaded or is null");
            return null;
        }

        tile_resource_location = new ResourceLocation(tile_model.domain, "textures/" + tile_model.path);

        return tile_resource_location;
    }

    public static ResourceLocation receiveSoundPath(Sound sound){
        ResourceLocation sound_resource_location = new ResourceLocation(sound.getDomain(), "models/sounds/" + sound.getRegistryName() + ".json");

        try {
            Reader sound_reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(sound_resource_location.toString())));
            sound_model = parser.fromJson(sound_reader, Model.class);
        } catch (NullPointerException e){
            System.out.println(sound_resource_location +" could not be loaded or is null");
            return null;
        }

        sound_resource_location = new ResourceLocation(sound_model.domain, "sounds/" + sound_model.path);

        return sound_resource_location;
    }

    public static ResourceLocation receiveImagePath(Entity entity) {
        ResourceLocation entity_resource_location = new ResourceLocation(entity.getDomain(), "models/entities/" + entity.getRegistryName() + ".json");

        try {
            Reader entity_reader = new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(entity_resource_location.toString())));
            entity_model = parser.fromJson(entity_reader, Model.class);
        } catch (NullPointerException e){
            System.out.println(entity_resource_location +" could not be loaded or is null");
            return null;
        }

        entity_resource_location = new ResourceLocation(entity_model.domain, "textures/" + entity_model.path);

        return entity_resource_location;

    }
}
