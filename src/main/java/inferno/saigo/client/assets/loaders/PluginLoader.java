package inferno.saigo.client.assets.loaders;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import inferno.saigo.api.plugins.Plugin;
import inferno.saigo.api.plugins.PluginFactory;

public class PluginLoader {

    private final HashMap<String, PluginFactory> pluginFactoryMap = new HashMap<>();
    private final File pluginsDir;
    private final AtomicBoolean loading = new AtomicBoolean();

    public PluginLoader(File pluginsDir) {
        this.pluginsDir = pluginsDir;
    }

    public void loadPlugins() {
        if (!pluginsDir.exists() || !pluginsDir.isDirectory()) {
            System.err.println("Skipping Plugin Loading. Plugin dir not found: " + pluginsDir);
            return;
        }

        if (loading.compareAndSet(false, true)) {
            System.out.println("Plugin Loading started. Plugin dir found: " + pluginsDir);
            final File[] files = Objects.requireNonNull(pluginsDir.listFiles());
            for (File pluginDir : files) {
                if (pluginDir.isDirectory()) {
                    loadPlugin(pluginDir);
                }
            }
        }
    }

    private void loadPlugin(final File pluginDir) {
        System.out.println("Loading plugin: " + pluginDir);
        final URLClassLoader pluginClassLoader = createPluginClassLoader(pluginDir);
        final ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(pluginClassLoader);
            for (Plugin plugin : ServiceLoader.load(Plugin.class, pluginClassLoader)) {
                installPlugin(plugin);
            }
        } finally {
            Thread.currentThread().setContextClassLoader(currentClassLoader);
        }
    }


    private void installPlugin(final Plugin plugin) {
        System.out.println("Installing plugin: " + plugin.getClass().getName());
        for (PluginFactory f : plugin.getPluginFactories()) {
            pluginFactoryMap.put(f.name(), f);
        }
    }

    private URLClassLoader createPluginClassLoader(File dir) {
        final URL[] urls = Arrays.stream(Optional.of(dir.listFiles()).orElse(new File[]{}))
                .sorted()
                .map(File::toURI)
                .map(this::toUrl)
                .toArray(URL[]::new);

        return new PluginClassLoader(urls, getClass().getClassLoader());
    }

    private URL toUrl(URI uri) {
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public PluginFactory getPluginFactory(String name) {
        return pluginFactoryMap.get(name);
    }

    public HashMap<String, PluginFactory> getPluginFactoriesMap(){
        return pluginFactoryMap;
    }
}
