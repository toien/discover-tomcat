package ch3.http.processor;

import java.io.IOException;
import java.net.Socket;

import ch3.http.connector.HttpConnector;

public class HttpProcessor {
	
	HttpConnector connector = null;
	
	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}

	public void process(Socket socket) {
		
		SocketInputStream input = new SocketInputStream(socket.getInputStream(), 2048);
		/*try (
				) {
			
		} catch (IOException e) {
			
		}
		
		Request request = new Request(input);
		request.parse();
		
		if (request.getUri() != null) {
			Response response = new Response(output);
			response.setRequest(request);
			
			if (request.getUri().startsWith("/servlet/")) {
				ServletProcessor processor = new ServletProcessor();
				processor.process(request, response);
			} else {
				StaticResourceProcessor processor = new StaticResourceProcessor();
				processor.process(request, response);
			}
			shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
		}*/

	}
}


