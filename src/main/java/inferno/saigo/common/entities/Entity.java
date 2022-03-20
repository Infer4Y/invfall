package inferno.saigo.common.entities;

import inferno.saigo.common.configuration.Settings;
import inferno.saigo.common.maps.MapWorld;

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

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public void onCollision(MapWorld world, EntityData data) {}

    public void onUpdate(MapWorld world, EntityData self){}
}
