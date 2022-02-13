package inferno.saigo.common.tiles;

import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.items.Item;
import inferno.saigo.common.items.ItemTile;

public class Tile {
    private final String name;
    private String domain = Settings.name.toLowerCase();

    private final ItemTile item_tile;

    public Tile(String name) {
        this.name = name;
        this.item_tile = new ItemTile(this);
    }

    public String getName() {
        return name;
    }

    public Item getTileItem() {
        return item_tile;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return domain + ":" + name;
    }
}
