package ch2;

import java.net.URLClassLoader;

public class ServletProcessor {
	public void process(Request request, Response response) {
		String uri = request.getUri();
		String serlvetName = uri.substring(uri.lastIndexOf("/") + 1);
		
		URLClassLoader loader = null;
		
		
	}
}
