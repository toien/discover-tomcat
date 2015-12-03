package ch3.startup;

import ch3.connector.http.HttpConnector;

public class Bootstrap {
	public static void main(String[] args) {
		HttpConnector connector = new HttpConnector();
		connector.start();
	}
}
