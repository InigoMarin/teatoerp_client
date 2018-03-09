package com.domusateknik.rest;

import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class App {

	static Client client = Client.create();

	private static final Logger logger = Logger.getLogger("App");
	private static final String APP_NAME = "Inteface.jar";

	public static void main(String[] args) {
		// Poner log en una sola linea
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optAccion = Option.builder("accion").longOpt("accion")
				.desc("Ejemplo: accion=articulo o accion=estructura").numberOfArgs(2).argName("accion").build();

		Option optEstado = Option.builder("estado").longOpt("estado").desc("Ejemplo: estado=validar").numberOfArgs(2)
				.argName("estado").build();

		options.addOption(optAccion);
		options.addOption(optEstado);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("accion") && cmd.hasOption("estado")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = cmd.getOptionValue("accion").toLowerCase();
			String estado = cmd.getOptionValue("estado").toLowerCase();

			logger.info("Acccion=" + cmd.getOptionValue("accion"));
			logger.info("Estado=" + cmd.getOptionValue("estado"));

			enviarDatosServidor(accion, estado);
		}
	}

	public static void enviarDatosServidor(String accion, String estado) {
		logger.info("Iniciado proceso " + estado + " " + accion);
		String urlPath = "http://localhost:8083/api/";
		// String getUrl =
		// "http://localhost:8083/api/articulo?fase=VALIDAR&file=MaestroArticulo.xml";
		String getUrl = urlPath + accion + "?fase=" + estado + "&file=" + accion + ".xml";
		logger.info("Connectando ->" + getUrl);
		WebResource webResource = client.resource(getUrl);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		logger.info("Http Status  " + response.getStatus());
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);

		if (result.equals("false")) {
			String error = "Articulo no validado.";
			logger.severe(error);
			throw new RuntimeException(error);
		} else {
			logger.info("Articulo validado.");
		}

		System.out.println("Response from the Server: " + result);
	}
}
