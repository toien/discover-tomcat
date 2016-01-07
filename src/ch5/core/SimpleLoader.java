package ch5.core;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import org.apache.catalina.Container;
import org.apache.catalina.DefaultContext;
import org.apache.catalina.Loader;

import ch5.Constants;

public class SimpleLoader implements Loader {

	ClassLoader classLoader;
	Container container;

	public SimpleLoader() {
		try {
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			String repository = new URL("file", null,
					(classPath.getCanonicalPath() + File.separator)).toString();

			urls[0] = new URL(null, repository, streamHandler);
			classLoader = new URLClassLoader(urls);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public void setContainer(Container container) {
		this.container = container;
	}

	@Override
	public DefaultContext getDefaultContext() {

		return null;
	}

	@Override
	public void setDefaultContext(DefaultContext defaultContext) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean getDelegate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDelegate(boolean delegate) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getInfo() {
		return "A simple loader";
	}

	@Override
	public boolean getReloadable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReloadable(boolean reloadable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRepository(String repository) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] findRepositories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

}
