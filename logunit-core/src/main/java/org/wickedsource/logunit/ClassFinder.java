package org.wickedsource.logunit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Finds all classes in given packages. Source of this code: <a href=
 * "http://internna.blogspot.de/2007/11/java-5-retrieving-all-classes-from.html"
 * >http://internna.blogspot.de/2007/11/java-5-retrieving-all-classes-from.html</a>.
 * 
 * @author Tom Hombergs (tom.hombergs@gmail.com)
 * 
 */
public class ClassFinder {

	/**
	 * Retrieves all {@link Class}es from the classpath in the given package and its sub packages.
	 * 
	 * @param loader
	 *            the {@link ClassLoader} to use when looking for the classes
	 * @param packageName
	 *            the full qualified name of the package in which to search.
	 * @return a set containing all classes within the specified package
	 */
	public Set<Class<?>> getClasses(final ClassLoader loader, final String packageName) {
		try {
			Set<Class<?>> classes = new HashSet<Class<?>>();
			String path = packageName.replace('.', '/');
			Enumeration<URL> resources = loader.getResources(path);
			if (resources != null) {
				while (resources.hasMoreElements()) {
					String filePath = resources.nextElement().getFile();
					// WINDOWS HACK
					if (filePath.indexOf("%20") > 0) {
						filePath = filePath.replaceAll("%20", " ");
					}
					if (filePath != null) {
						if ((filePath.indexOf("!") > 0) && (filePath.indexOf(".jar") > 0)) {
							String jarPath = filePath.substring(0, filePath.indexOf("!")).substring(
									filePath.indexOf(":") + 1);
							// WINDOWS HACK
							if (jarPath.indexOf(":") >= 0) {
								jarPath = jarPath.substring(1);
							}
							classes.addAll(getFromJARFile(jarPath, path));
						} else {
							classes.addAll(getFromDirectory(new File(filePath), packageName));
						}
					}
				}
			}
			return classes;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieves all {@link Class}es from the classpath in the given packages and their sub packages.
	 * 
	 * @param loader
	 *            the {@link ClassLoader} to use when looking for the classes
	 * @param packageName
	 *            the full qualified name of the package in which to search.
	 * @return a set containing all classes within the specified packages
	 */
	public Set<Class<?>> getClasses(final ClassLoader loader, final String... packageNames)
			throws ClassNotFoundException, IOException {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (String packageName : packageNames) {
			classes.addAll(getClasses(loader, packageName));
		}
		return classes;
	}

	private Set<Class<?>> getFromDirectory(final File directory, final String packageName)
			throws ClassNotFoundException {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		if (directory.exists()) {
			for (String file : directory.list()) {
				if (file.endsWith(".class")) {
					String name = packageName + '.' + stripFilenameExtension(file);
					Class<?> clazz = Class.forName(name);
					classes.add(clazz);
				}
			}
		}
		return classes;
	}

	private Set<Class<?>> getFromJARFile(final String jar, final String packageName) throws IOException,
			ClassNotFoundException {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		JarInputStream jarFile = new JarInputStream(new FileInputStream(jar));
		JarEntry jarEntry;
		do {
			jarEntry = jarFile.getNextJarEntry();
			if (jarEntry != null) {
				String className = jarEntry.getName();
				if (className.endsWith(".class")) {
					className = stripFilenameExtension(className);
					if (className.startsWith(packageName)) {
						classes.add(Class.forName(className.replace('/', '.')));
					}
				}
			}
		} while (jarEntry != null);
		return classes;
	}

	private String stripFilenameExtension(final String file) {
		int index = file.lastIndexOf(".");
		return file.substring(0, index);
	}

}
