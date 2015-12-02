package ch3.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import ch2.ServletProcessor;
import ch3.connector.http.HttpConnector;

public class HttpProcessor {

	HttpConnector connector;
	
	HttpRequest request;
	
	HttpResponse response;

	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}

	public void process(Socket socket) {
		try (SocketInputStream input = new SocketInputStream(socket.getInputStream(), 2048);
				OutputStream output = socket.getOutputStream();) {
			
			// create  HttpRequest object and parse
			request = new HttpRequest(input);
			
			// create  HttpResponse object
			response = new HttpResponse(output);
			response.setRequest(request);
			
			response.setHeader("Server", "Comes from Toien server");
			
			if(request.getRequestURI().startsWith("/servlet/")) {
				ServletProcessor processor = new ServletProcessor();
				processor.process(request, response);
			} else {
				
			}
			
			
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
			}
		} catch (IOException e) {

		}


	}
}
