package ch2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer {
	public static final String WEB_ROOT = "D:/Develop Tools/apache-tomcat-8.0.23/webapps/ROOT";
	public static final String SHUTDOWN_COMMAND = "shutdown";

	private boolean shutdown = false;
	private String host = "localhost";
	private int port = 8080;

	public void await() {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName(host));
		} catch (IOException e) {
			e.printStackTrace();

		}

		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;

			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();

				Request request = new Request(input);
				request.parse();

				Response response = new Response(output);
				response.setRequest(request);

				if (request.getUri().startsWith("/servlet/")) {
					ServletProcessor processor = new ServletProcessor();
					processor.process(request, response);
				} else {
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}

				socket.close();

				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (IOException re) {
				re.printStackTrace();
				continue;
			}

		}
	}

	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}
}
