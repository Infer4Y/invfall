package inferno.saigo.api.plugins;

import java.util.Collections;
import java.util.List;

public interface Plugin {
    default List<PluginFactory> getPluginFactories() {
        return Collections.emptyList();
    }
}
