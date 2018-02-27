package com.domusateknik.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class App {

	static Client client = Client.create();

	// set the appropriate URL
	// static String getUrl =
	// "http://localhost:8083/api/articulo?fase=VALIDAR&file=MaestroArticulo.xml";
	static String getUrl = "http://localhost:8083/api/articulo?fase=VALIDAR&file=MaestroArticulo.xml";

	public static void main(String[] args) {
		getRequest();
	}

	public static void getRequest() {
		WebResource webResource = client.resource(getUrl);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		// a successful response returns 200

		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);

		if (result.equals("false")) {
			throw new RuntimeException("Articulo no validado.");
		}

		System.out.println("Response from the Server: ");
		System.out.println(result);
	}
}
