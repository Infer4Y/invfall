package inferno.saigo.common.entities;

import inferno.saigo.common.configuration.Settings;

public class Entity {
    private final String registryName;
    private String domain = Settings.name.toLowerCase();
    private int maxAge;

    public Entity(String registryName) {
        this.registryName = registryName;
    }

    public String getRegistryName() {
        return registryName;
    }

    public String getDomain() {
        return domain;
    }

    public Entity setDomain(String domain) {
        this.domain = domain;

        return this;
    }

    @Override
    public String toString() {
        return domain + ":entities." + registryName;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public Entity setMaxAge(int maxAge) {
        this.maxAge = maxAge;

        return this;
    }

    public void onCollision(EntityData data) {

    }
}
