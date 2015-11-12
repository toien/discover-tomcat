package ch1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

	private static final int BUFFER_SIZE = 1024;

	private Request request;
	private OutputStream output;

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response(OutputStream output) {
		this.output = output;
	}

	public void sendStaticResource() {
		byte[] bytes = new byte[BUFFER_SIZE];

		File file = new File(HttpServer.WEB_ROOT, request.getUri());
		try (FileInputStream fis = new FileInputStream(file)) {
			if (file.exists()) {
				int ch = fis.read(bytes, 0, BUFFER_SIZE);
				while (ch != -1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, BUFFER_SIZE);
				}
			} else {
				// @formatter:off
				String errorMsg = "HTTP/1.1 404 File Not Found\r\n" + 
						"Content-Type: text/html\r\n" + 
						"Content-Length: 23\r\n" + 
						"\r\n" + 
						"<h1>File Not Found</h1>";
				// @formatter:on
				output.write(errorMsg.getBytes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
