package inferno.saigo.client.assets.loaders;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.List;

public class PluginClassLoader extends URLClassLoader {

    public static final List<String> SHARED_PACKAGES = Collections.singletonList("inferno.saigo.api");

    private final ClassLoader parentClassLoader;

    public PluginClassLoader(URL[] urls, ClassLoader parentClassLoader) {
        super(urls, null);
        this.parentClassLoader = parentClassLoader;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        // has the class loaded already?
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            final boolean isSharedClass = SHARED_PACKAGES.stream().anyMatch(name::startsWith);
            if (isSharedClass) {
                loadedClass = parentClassLoader.loadClass(name);
            } else {
                loadedClass = super.loadClass(name, resolve);
            }
        }

        if (resolve) {      // marked to resolve
            resolveClass(loadedClass);
        }
        return loadedClass;
    }
}
