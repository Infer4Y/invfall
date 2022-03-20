package inferno.saigo.client.assets.loaders;
import static java.util.Objects.requireNonNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

import inferno.saigo.api.plugins.Plugin;
import inferno.saigo.api.plugins.PluginFactory;

public class PluginLoader {

    private final HashMap<String, PluginFactory> pluginFactoryMap = new HashMap<>();
    private final File pluginsDir;
    private final AtomicBoolean loading = new AtomicBoolean();

    public PluginLoader(final File pluginsDir) {
        this.pluginsDir = pluginsDir;
    }

    public void loadPlugins() {
        if (!pluginsDir.exists() || !pluginsDir.isDirectory()) {
            System.err.println("Skipping Plugin Loading. Plugin dir not found: " + pluginsDir);
            return;
        }

        if (loading.compareAndSet(false, true)) {
            final File[] files = requireNonNull(pluginsDir.listFiles());
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
        plugin.getPluginFactories().forEach(pluginFactory -> pluginFactoryMap.put(pluginFactory.domain(), pluginFactory));
    }

    private URLClassLoader createPluginClassLoader(File dir) {
        final URL[] urls = Arrays.stream(Optional.of(requireNonNull(dir.listFiles())).orElse(new File[]{}))
                .sorted()
                .map(File::toURI)
                .map(this::toUrl)
                .toArray(URL[]::new);

        return new PluginClassLoader(urls, getClass().getClassLoader());
    }

    private URL toUrl(final URI uri) {
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public PluginFactory getPluginFactory(String name) {
        return pluginFactoryMap.get(name);
    }

    public HashMap<String, PluginFactory> getPluginFactories(){
        return pluginFactoryMap;
    }
}
