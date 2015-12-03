package ch3;

import java.io.IOException;

import ch3.connector.http.HttpRequest;
import ch3.connector.http.HttpResponse;

public class StaticResourceProcessor {
	public void process(HttpRequest request, HttpResponse response) {
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
