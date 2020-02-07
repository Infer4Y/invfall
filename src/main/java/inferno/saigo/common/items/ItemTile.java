package inferno.saigo.common.items;

import inferno.saigo.common.tiles.Tile;

public class ItemTile extends Item {
    private Tile reference;

    public ItemTile(Tile tile) {
        super(tile.getName());
        this.reference = tile;
    }

    public Tile getTile() {
        return reference;
    }
}
