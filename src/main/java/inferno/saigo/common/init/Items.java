package inferno.saigo.common.init;

import inferno.saigo.common.items.Item;

import java.util.HashMap;

public class Items {
    private static final HashMap<String, Item> ITEMS = new HashMap<>();
    public static final Item CURSOR = new Item("cursor");

    public static void init(){
        register(CURSOR);
    }

    public static Item getItem(String name) {
        return ITEMS.getOrDefault(name, null);
    }

    public static void register(Item item){
        ITEMS.put(item.getName(), item);
        //System.out.println(item.getName());
    }

    public static void register(Item... items){
        for (Item item: items) {
            register(item);
        }
    }
}
