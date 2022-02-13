package inferno.saigo.common.items;

import inferno.saigo.common.configuration.Settings;

public class Item {
    private String name;
    private String domain = Settings.name.toLowerCase();

    public Item(String name) {
        this.name = name;
    }

    public Item() {}

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
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
        return domain + ":" + name;
    }
}
