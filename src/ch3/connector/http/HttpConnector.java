package ch3.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import ch3.connector.http.processor.HttpProcessor;

public class HttpConnector implements Runnable {

	boolean stopped;
	String schema = "http";

	public String getSchema() {
		return schema;
	}

	@Override
	public void run() {
		int port = 8080;

		try (ServerSocket serverSocket = new ServerSocket(port, 1,
				InetAddress.getByName("127.0.0.1"))) {
			while (!stopped) {
				Socket socket = serverSocket.accept();
				
				HttpProcessor processor = new HttpProcessor(this);
				
				processor.process(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		Thread thread = new Thread(this);
		
		thread.start();
	}
}
