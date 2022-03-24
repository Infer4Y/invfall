package inferno.saigo.client.assets.loaders;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PluginClassLoader extends URLClassLoader {

    public static final List<String> SHARED_PACKAGES = Arrays.asList("inferno.saigo.api", "inferno.saigo.client", "inferno.saigo.common");

    private final ClassLoader parentClassLoader;

    public PluginClassLoader(URL[] urls, ClassLoader parentClassLoader) {
        super(urls, null);
        this.parentClassLoader = parentClassLoader;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class<?> loadedClass = this.findLoadedClass(name);
        if (loadedClass == null) {
            boolean isSharedClass = SHARED_PACKAGES.stream().anyMatch(name::startsWith);

            loadedClass = (isSharedClass) ? parentClassLoader.loadClass(name) : super.loadClass(name,resolve);
        }

        if (resolve) {      // marked to resolve
            this.resolveClass(loadedClass);
        }

        return loadedClass;
    }
}
