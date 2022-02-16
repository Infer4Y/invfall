package inferno.saigo.common.tiles;

import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.items.Item;
import inferno.saigo.common.items.ItemTile;

public class Tile {
    private final String registry_name;
    private String domain = Settings.name.toLowerCase();

    public Tile(String registry_name) {
        this.registry_name = registry_name;
    }

    public String getRegistryName() {
        return registry_name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return domain + ":tiles." + registry_name;
    }
}
