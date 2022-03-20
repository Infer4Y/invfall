package inferno.saigo.api.plugins;

public interface PluginFactory {
    String name();
    String domain();
    String version();

    Mod build();
}
