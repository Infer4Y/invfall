package inferno.saigo.common.tiles;

import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.items.Item;
import inferno.saigo.common.items.ItemTile;

public class Tile {
    private final String name;
    private String domain = Settings.name.toLowerCase();

    private boolean
            solid = false,
            air = false,
            tickable = false;
    private final ItemTile item_tile;

    public Tile(String name) {
        this.name = name;
        this.item_tile = new ItemTile(this);
    }

    public Tile(String name, boolean solid, boolean air, boolean tickable) {
        this.name = name;
        this.solid = solid;
        this.air = air;
        this.tickable = tickable;
        this.item_tile = new ItemTile(this);
    }

    public String getName() {
        return name;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isAir() {
        return air;
    }

    public boolean isTickable() {
        return tickable;
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
}
