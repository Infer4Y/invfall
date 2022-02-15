package inferno.saigo.common.items;

import inferno.saigo.common.configuration.Settings;

public class Item {
    private String registry_name;
    private String domain = Settings.name.toLowerCase();

    public Item(String registry_name) {
        this.registry_name = registry_name;
    }

    public Item() {}

    public String getRegistryName() {
        return registry_name;
    }

    public Item setRegistryName(String registry_name) {
        this.registry_name = registry_name;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public Item setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    @Override
    public String toString() {
        return domain + ":items." + registry_name;
    }
}
