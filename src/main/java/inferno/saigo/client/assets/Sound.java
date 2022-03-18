package inferno.saigo.client.assets;

import inferno.saigo.common.configuration.Settings;

public class Sound {
    private String registry_name;
    private String domain = Settings.name.toLowerCase();
    private Type soundType = Type.MUSIC;

    public String getRegistryName() {
        return registry_name;
    }

    public Sound setRegistryName(String registry_name) {
        this.registry_name = registry_name;

        return this;
    }

    public String getDomain() {
        return domain;
    }

    public Sound setDomain(String domain) {
        this.domain = domain;

        return this;
    }

    @Override
    public String toString() {
        return domain + ":sounds." + registry_name;
    }

    public Type getSoundType() {
        return soundType;
    }

    public Sound setSoundType(Type soundType) {
        this.soundType = soundType;

        return this;
    }

    public enum Type{
        MUSIC, WORLD, SPEECH
    }
}
