package inferno.saigo.common.init;

import inferno.saigo.common.items.Item;

import java.util.HashMap;

public class Items {
    private static final HashMap<String, Item> ITEMS = new HashMap<>();
    public static final Item COAL = new Item("coal");
    public static final Item RUBY = new Item("ruby");
    public static final Item DIAMOND = new Item("diamond");
    public static final Item INGOT = new Item("ingot");
    public static final Item STICK = new Item("stick");
    public static final Item PICKAXE = new Item("pickaxe");
    public static final Item SHOVEL = new Item("shovel");

    public static void init(){
        register(COAL, RUBY, DIAMOND, INGOT, STICK, PICKAXE, SHOVEL);
    }

    public static Item getItem(String name) {
        return ITEMS.getOrDefault(name, ITEMS.get("placeholder"));
    }

    public static void register(Item item){
        ITEMS.put(item.toString(), item);
        //System.out.println(item.getName());
    }

    public static void register(Item... items){
        for (Item item: items) {
            register(item);
        }
    }

    public static HashMap<String, Item> getItems() {
        return ITEMS;
    }
}
