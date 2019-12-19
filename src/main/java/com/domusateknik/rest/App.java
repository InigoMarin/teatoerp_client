package com.domusateknik.rest;

import java.io.IOException;
import java.net.URI;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.ws.rs.core.UriBuilder;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class App {
	static Client client = Client.create();

	private static final Logger logger = Logger.getLogger("App");
	private static final String APP_NAME = "Inteface.jar";

	private static final String resource = "http://vmtc1:8083/api/";
	private static final String resourceWeb = "http://vmtc1:8083/";

	// private static final String resource = "http://localhost:8083/api/";
	// private static final String resourceWeb = "http://localhost:8083/";

	public static void accionCorreoTexto(Option optAccion, String[] args) {
		logger.info("Accion Enviar correo Texto Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optTo = Option.builder("to").longOpt("to").desc("Ejemplo: to=imarin@domusateknik.com").numberOfArgs(2)
				.argName("to").build();

		Option optFrom = Option.builder("from").longOpt("from").desc("Ejemplo: from=imarin@domusateknik.com")
				.numberOfArgs(2).argName("from").build();

		Option optSubject = Option.builder("sub").longOpt("subject").desc("Ejemplo: subject= Asunto del correo")
				.numberOfArgs(2).argName("sub").build();

		Option optUser = Option.builder("user").longOpt("user").desc("Ejemplo: body=usuario").numberOfArgs(2)
				.argName("user").build();

		Option optBody = Option.builder("body").longOpt("body").desc("Ejemplo: body=mail.html o texto").numberOfArgs(2)
				.argName("body").build();

		Option optlinea1 = Option.builder("linea1").longOpt("linea1").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea1").build();

		Option optlinea2 = Option.builder("linea2").longOpt("linea2").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea2").build();

		Option optlinea3 = Option.builder("linea3").longOpt("linea3").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea3").build();

		Option optlinea4 = Option.builder("linea4").longOpt("linea4").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea4").build();

		Option optlinea5 = Option.builder("linea5").longOpt("linea5").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea5").build();

		Option optCondicionExiste = Option.builder("condExiste").longOpt("condExiste").desc("Ejemplo: true o false")
				.numberOfArgs(2).argName("condExiste").build();

		Option optCondicionItemRevision = Option.builder("condItemRevision").longOpt("condItemRevision")
				.desc("Ejemplo: true o false").numberOfArgs(2).argName("condItemRevision").build();

		Option optCondicionEan13 = Option.builder("condEan13").longOpt("condEan13").desc("Ejemplo: true o false")
				.numberOfArgs(2).argName("condEan13").build();

		Option optCod = Option.builder("cod").longOpt("cod").desc("Ejemplo: true o false").numberOfArgs(2)
				.argName("cod").build();

		Option optDesc = Option.builder("desc").longOpt("desc").desc("Ejemplo: true o false").numberOfArgs(2)
				.argName("desc").build();

		Option optCant = Option.builder("cant").longOpt("cant").desc("Ejemplo:1").numberOfArgs(2).argName("cant")
				.build();

		Option optnp = Option.builder("np").longOpt("np").desc("Ejemplo:Nota general").numberOfArgs(2).argName("np")
				.build();

		Option optng = Option.builder("ng").longOpt("ng").desc("Ejemplo:Nota particular").numberOfArgs(2).argName("ng")
				.build();

		Option optrevid = Option.builder("revId").longOpt("revId").desc("Ejemplo:Nota particular").numberOfArgs(2)
				.argName("revId").build();

		Option optitemid = Option.builder("itemId").longOpt("itemId").desc("Ejemplo:Nota particular").numberOfArgs(2)
				.argName("itemId").build();

		Option optean13 = Option.builder("ean13").longOpt("ean13").desc("Ejemplo:Nota particular").numberOfArgs(2)
				.argName("ean13").build();

		options.addOption(optAccion);
		options.addOption(optTo);
		options.addOption(optFrom);
		options.addOption(optSubject);
		options.addOption(optUser);
		options.addOption(optBody);

		options.addOption(optlinea1);
		options.addOption(optlinea2);
		options.addOption(optlinea3);
		options.addOption(optlinea4);
		options.addOption(optlinea5);

		options.addOption(optCondicionExiste);
		options.addOption(optCondicionItemRevision);
		options.addOption(optCondicionEan13);

		options.addOption(optCod);
		options.addOption(optDesc);
		options.addOption(optCant);
		options.addOption(optnp);
		options.addOption(optng);
		options.addOption(optitemid);
		options.addOption(optrevid);
		options.addOption(optean13);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("accion") && cmd.hasOption("to") && cmd.hasOption("from") && cmd.hasOption("sub")
					&& cmd.hasOption("user") && cmd.hasOption("body")) {
				cmdOK = true;

			} else {
				formatter.printHelp(APP_NAME, options);
				logger.severe("Falta parametros.");
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {

			String getUrl = "";

			String accion = cmd.getOptionValue("accion").toLowerCase();
			String to = cmd.getOptionValue("to").toLowerCase();
			String from = cmd.getOptionValue("from").toLowerCase();

			String subject = cmd.getOptionValue("sub");
			String body = cmd.getOptionValue("body");
			String user = cmd.getOptionValue("user");
			String linea1 = cmd.getOptionValue("linea1");
			String linea2 = cmd.getOptionValue("linea2");
			String linea3 = cmd.getOptionValue("linea3");
			String linea4 = cmd.getOptionValue("linea4");
			String linea5 = cmd.getOptionValue("linea5");
			String cod = cmd.getOptionValue("cod");
			String desc = cmd.getOptionValue("desc");
			String cant = cmd.getOptionValue("cant");
			String np = cmd.getOptionValue("np");
			String ng = cmd.getOptionValue("ng");
			String itemid = cmd.getOptionValue("itemId");
			String revid = cmd.getOptionValue("revId");
			String ean13 = cmd.getOptionValue("ean13");

			Boolean condicionExiste = false;
			try {
				String condExiste = cmd.getOptionValue("condExiste").toUpperCase();

				if (condExiste == null || condExiste.equals("FALSO")) {
					condicionExiste = false;
				} else {
					condicionExiste = true;
				}
			} catch (Exception e) {
				condicionExiste = false;
			}

			Boolean condicionItemRevision = false;
			try {
				String condItemRevision = cmd.getOptionValue("condItemRevision").toUpperCase();

				if (condItemRevision == null || condItemRevision.equals("FALSO")) {
					condicionItemRevision = false;
				} else {
					condicionItemRevision = true;
				}
			} catch (Exception e) {
				condicionItemRevision = false;
			}

			Boolean condicionEan13 = false;
			try {
				String condEan13 = cmd.getOptionValue("condEan13").toUpperCase();

				if (condEan13 == null || condEan13.equals("FALSO")) {
					condicionEan13 = false;
				} else {
					condicionEan13 = true;
				}
			} catch (Exception e) {
				condicionEan13 = false;
			}

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("to=" + to);
			logger.info("from=" + from);
			logger.info("subject=" + subject);
			logger.info("body=" + body);
			logger.info("user=" + user);
			logger.info("linea1=" + linea1);
			logger.info("linea2=" + linea2);
			logger.info("linea3=" + linea3);
			logger.info("linea4=" + linea4);
			logger.info("linea5=" + linea5);
			logger.info("condExiste=" + condicionExiste);
			logger.info("condEan13=" + condicionEan13);
			logger.info("condItemrevision=" + condicionItemRevision);
			logger.info("cod=" + cod);
			logger.info("desc=" + desc);
			logger.info("cant=" + cant);
			logger.info("np=" + np);
			logger.info("ng=" + ng);
			logger.info("revid=" + revid);
			logger.info("itemid=" + itemid);
			logger.info("ean13=" + ean13);
			logger.info("******************");

			getUrl = resource + accion;

			JSONObject json = new JSONObject();

			json.put("to", to);
			json.put("from", from);
			json.put("subject", subject);
			json.put("body", body);
			json.put("user", user);
			json.put("linea1", linea1);
			json.put("linea2", linea2);
			json.put("linea3", linea3);
			json.put("linea4", linea4);
			json.put("linea5", linea5);
			json.put("condicionExiste", condicionExiste);
			json.put("condicionItemRevision", condicionItemRevision);
			json.put("condicionEan13", condicionEan13);

			json.put("codigo", cod);
			json.put("descripcion", desc);
			json.put("cantidad", cant);
			json.put("notaparticular", np);
			json.put("notageneral", ng);
			json.put("revId", revid);
			json.put("itemId", itemid);
			json.put("ean13", ean13);

			String parameter = json.toJSONString();

			enviarDatosServidorPost(getUrl, parameter);

		}
		logger.info("Accion Enviar correo Texto Finalizar.");

	}

	public static void accionCorreoPlantillaCondicion(Option optAccion, String[] args) {
		logger.info("Accion Enviar correo Texto Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optTo = Option.builder("to").longOpt("to").desc("Ejemplo: to=imarin@domusateknik.com").numberOfArgs(2)
				.argName("to").build();

		Option optFrom = Option.builder("from").longOpt("from").desc("Ejemplo: from=imarin@domusateknik.com")
				.numberOfArgs(2).argName("from").build();

		Option optSubject = Option.builder("sub").longOpt("subject").desc("Ejemplo: subject= Asunto del correo")
				.numberOfArgs(2).argName("sub").build();

		Option optUser = Option.builder("user").longOpt("user").desc("Ejemplo: body=usuario").numberOfArgs(2)
				.argName("user").build();

		Option optBody = Option.builder("body").longOpt("body").desc("Ejemplo: body=mail.html o texto").numberOfArgs(2)
				.argName("body").build();

		Option optlinea1 = Option.builder("linea1").longOpt("linea1").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea1").build();

		Option optlinea2 = Option.builder("linea2").longOpt("linea2").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea2").build();

		Option optlinea3 = Option.builder("linea3").longOpt("linea3").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea3").build();

		Option optlinea4 = Option.builder("linea4").longOpt("linea4").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea4").build();

		Option optlinea5 = Option.builder("linea5").longOpt("linea5").desc("Ejemplo: body=mail.html o texto")
				.numberOfArgs(2).argName("linea5").build();

		Option optCondicionExiste = Option.builder("condExiste").longOpt("condExiste").desc("Ejemplo: true o false")
				.numberOfArgs(2).argName("condExiste").build();

		Option optCondicionItemRevision = Option.builder("condItemRevision").longOpt("condItemRevision")
				.desc("Ejemplo: true o false").numberOfArgs(2).argName("condItemRevision").build();

		Option optCondicionEan13 = Option.builder("condEan13").longOpt("condEan13").desc("Ejemplo: true o false")
				.numberOfArgs(2).argName("condEan13").build();

		Option optTemplate = Option.builder("template").longOpt("template").desc("Ejemplo: true o false")
				.numberOfArgs(2).argName("template").build();

		Option optCod = Option.builder("cod").longOpt("cod").desc("Ejemplo: true o false").numberOfArgs(2)
				.argName("cod").build();

		Option optDesc = Option.builder("desc").longOpt("desc").desc("Ejemplo: true o false").numberOfArgs(2)
				.argName("desc").build();

		Option optCant = Option.builder("cant").longOpt("cant").desc("Ejemplo:1").numberOfArgs(2).argName("cant")
				.build();

		Option optnp = Option.builder("np").longOpt("np").desc("Ejemplo:Nota general").numberOfArgs(2).argName("np")
				.build();

		Option optng = Option.builder("ng").longOpt("ng").desc("Ejemplo:Nota particular").numberOfArgs(2).argName("ng")
				.build();

		Option optrevid = Option.builder("revId").longOpt("revId").desc("Ejemplo:Nota particular").numberOfArgs(2)
				.argName("revId").build();

		Option optitemid = Option.builder("itemId").longOpt("itemId").desc("Ejemplo:Nota particular").numberOfArgs(2)
				.argName("itemId").build();

		Option optean13 = Option.builder("ean13").longOpt("ean13").desc("Ejemplo:Nota particular").numberOfArgs(2)
				.argName("ean13").build();

		options.addOption(optAccion);
		options.addOption(optTo);
		options.addOption(optFrom);
		options.addOption(optSubject);
		options.addOption(optUser);
		options.addOption(optBody);

		options.addOption(optlinea1);
		options.addOption(optlinea2);
		options.addOption(optlinea3);
		options.addOption(optlinea4);
		options.addOption(optlinea5);

		options.addOption(optCondicionExiste);
		options.addOption(optCondicionItemRevision);
		options.addOption(optCondicionEan13);
		options.addOption(optTemplate);
		options.addOption(optCod);
		options.addOption(optDesc);
		options.addOption(optCant);
		options.addOption(optnp);
		options.addOption(optng);
		options.addOption(optrevid);
		options.addOption(optitemid);
		options.addOption(optean13);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("accion") && cmd.hasOption("to") && cmd.hasOption("from") && cmd.hasOption("sub")
					&& cmd.hasOption("user") && cmd.hasOption("template")) {
				cmdOK = true;

			} else {
				formatter.printHelp(APP_NAME, options);

				String accion = cmd.getOptionValue("accion").toLowerCase();
				String to = cmd.getOptionValue("to").toLowerCase();
				String from = cmd.getOptionValue("from").toLowerCase();
				String subject = cmd.getOptionValue("sub");
				String user = cmd.getOptionValue("user");
				String template = cmd.getOptionValue("template");

				logger.info("accion=" + accion);
				logger.info("to=" + to);
				logger.info("from=" + from);
				logger.info("subject=" + subject);
				logger.info("user=" + user);
				logger.info("template=" + template);

				logger.severe("Falta parametros.");
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {

			String getUrl = "";

			String accion = cmd.getOptionValue("accion").toLowerCase();
			String to = cmd.getOptionValue("to").toLowerCase();
			String from = cmd.getOptionValue("from").toLowerCase();

			String subject = cmd.getOptionValue("sub");
			String body = cmd.getOptionValue("body");
			String user = cmd.getOptionValue("user");
			String linea1 = cmd.getOptionValue("linea1");
			String linea2 = cmd.getOptionValue("linea2");
			String linea3 = cmd.getOptionValue("linea3");
			String linea4 = cmd.getOptionValue("linea4");
			String linea5 = cmd.getOptionValue("linea5");
			String template = cmd.getOptionValue("template");
			String cod = cmd.getOptionValue("cod");
			String desc = cmd.getOptionValue("desc");
			String cant = cmd.getOptionValue("cant");
			String np = cmd.getOptionValue("np");
			String ng = cmd.getOptionValue("ng");
			String itemId = cmd.getOptionValue("itemId");
			String revId = cmd.getOptionValue("revId");
			String ean13 = cmd.getOptionValue("ean13");

			Boolean condicionExiste = false;
			try {
				String condExiste = cmd.getOptionValue("condExiste").toUpperCase();

				if (condExiste == null || condExiste.equals("FALSO")) {
					condicionExiste = false;
				} else {
					condicionExiste = true;
				}
			} catch (Exception e) {
				condicionExiste = false;
			}

			Boolean condicionItemRevision = false;
			try {
				String condItemRevision = cmd.getOptionValue("condItemRevision").toUpperCase();

				if (condItemRevision == null || condItemRevision.equals("FALSO")) {
					condicionItemRevision = false;
				} else {
					condicionItemRevision = true;
				}
			} catch (Exception e) {
				condicionItemRevision = false;
			}

			Boolean condicionEan13 = false;
			try {
				String condEan13 = cmd.getOptionValue("condEan13").toUpperCase();

				if (condEan13 == null || condEan13.equals("FALSO")) {
					condicionEan13 = false;
				} else {
					condicionEan13 = true;
				}
			} catch (Exception e) {
				condicionEan13 = false;
			}

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("to=" + to);
			logger.info("from=" + from);
			logger.info("subject=" + subject);
			logger.info("body=" + body);
			logger.info("user=" + user);
			logger.info("linea1=" + linea1);
			logger.info("linea2=" + linea2);
			logger.info("linea3=" + linea3);
			logger.info("linea4=" + linea4);
			logger.info("linea5=" + linea5);
			logger.info("condExiste=" + condicionExiste);
			logger.info("condEan13=" + condicionEan13);
			logger.info("condItemRevision=" + condicionItemRevision);
			logger.info("template=" + template);
			logger.info("cod=" + cod);
			logger.info("desc=" + desc);
			logger.info("cant=" + cant);
			logger.info("np=" + np);
			logger.info("ng=" + ng);
			logger.info("revId=" + revId);
			logger.info("itemId=" + itemId);
			logger.info("ean13=" + ean13);
			logger.info("******************");

			getUrl = resource + accion;

			JSONObject json = new JSONObject();

			json.put("to", to);
			json.put("from", from);
			json.put("subject", subject);
			json.put("body", body);
			json.put("user", user);

			json.put("linea1", linea1);
			json.put("linea2", linea2);
			json.put("linea3", linea3);
			json.put("linea4", linea4);
			json.put("linea5", linea5);

			json.put("template", template);

			json.put("condicionExiste", condicionExiste);
			json.put("condicionItemRevision", condicionItemRevision);
			json.put("condicionEan13", condicionEan13);
			json.put("codigo", cod);
			json.put("descripcion", desc);
			json.put("cantidad", cant);
			json.put("notaparticular", np);
			json.put("notageneral", ng);
			json.put("revId", revId);
			json.put("itemId", itemId);
			json.put("ean13", ean13);

			String parameter = json.toJSONString();

			enviarDatosServidorPost(getUrl, parameter);

		}
		logger.info("Accion Enviar correo Texto Finalizar.");

	}

	public static void accionCorreoCreacionArticulo(Option optAccion, String[] args) {
		logger.info("Accion Enviar correo Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optUser = Option.builder("user").longOpt("user").desc("Ejemplo: body=usuario").numberOfArgs(2)
				.argName("user").build();

		Option optFrom = Option.builder("from").longOpt("from").desc("Ejemplo: from=imarin@domusateknik.com")
				.numberOfArgs(2).argName("from").build();

		Option optSubject = Option.builder("sub").longOpt("subject").desc("Ejemplo: subject= Asunto del correo")
				.numberOfArgs(2).argName("sub").build();

		Option optTo = Option.builder("to").longOpt("to").desc("Ejemplo: to=imarin@domusateknik.com").numberOfArgs(2)
				.argName("to").build();

		Option optTemplate = Option.builder("template").longOpt("template").desc("creararticulo").numberOfArgs(2)
				.argName("template").build();

		Option optDesc = Option.builder("desc").longOpt("desc").desc("Se ha modificado").numberOfArgs(2).argName("desc")
				.build();

		Option optCod = Option.builder("cod").longOpt("cod").desc("Se ha modificado").numberOfArgs(2).argName("cod")
				.build();

		Option optEan13 = Option.builder("ean13").longOpt("ean13").desc("Se ha modificado").numberOfArgs(2)
				.argName("ean13").build();

		options.addOption(optAccion);
		options.addOption(optUser);
		options.addOption(optFrom);
		options.addOption(optSubject);
		options.addOption(optTo);
		options.addOption(optTemplate);
		options.addOption(optDesc);
		options.addOption(optCod);
		options.addOption(optEan13);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("accion") && cmd.hasOption("to") && cmd.hasOption("from") && cmd.hasOption("sub")
					&& cmd.hasOption("user") && cmd.hasOption("template") && cmd.hasOption("template")) {
				cmdOK = true;

			} else {
				formatter.printHelp(APP_NAME, options);
				logger.severe("Falta parametros.");
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {

			String getUrl = "";

			String accion = cmd.getOptionValue("accion").toLowerCase();
			String user = cmd.getOptionValue("user");
			String from = cmd.getOptionValue("from").toLowerCase();
			String subject = cmd.getOptionValue("sub");
			String to = cmd.getOptionValue("to").toLowerCase();
			String template = cmd.getOptionValue("template");
			String desc = cmd.getOptionValue("desc");
			String cod = cmd.getOptionValue("cod");
			String ean13 = cmd.getOptionValue("ean13");

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("user=" + user);
			logger.info("from=" + from);
			logger.info("subject=" + subject);
			logger.info("to=" + to);
			logger.info("template=" + template);
			logger.info("descripcion=" + desc);
			logger.info("codigo=" + cod);
			logger.info("ean13=" + ean13);
			logger.info("******************");

			// FORMATO GET
			/*
			 * getUrl = resource + accion + "?to=" + to + "&from=" + from + "&subject=" +
			 * subject + "&body=" + body + "&user=" + user;
			 * 
			 * enviarDatosServidor(getUrl);
			 */

			getUrl = resource + accion;

			JSONObject json = new JSONObject();

			json.put("user", user);
			json.put("from", from);
			json.put("subject", subject);
			json.put("to", to);
			json.put("template", template);
			json.put("descripcion", desc);
			json.put("codigo", cod);
			json.put("ean13", ean13);

			String parameter = json.toJSONString();

			enviarDatosServidorPost(getUrl, parameter);

		}
		logger.info("Accion Enviar correo Finalizar.");

	}

	public static void accionCorreoModificarArticulo(Option optAccion, String[] args) {
		logger.info("Accion Enviar correo Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optUser = Option.builder("user").longOpt("user").desc("Ejemplo: body=usuario").numberOfArgs(2)
				.argName("user").build();

		Option optFrom = Option.builder("from").longOpt("from").desc("Ejemplo: from=imarin@domusateknik.com")
				.numberOfArgs(2).argName("from").build();

		Option optSubject = Option.builder("sub").longOpt("subject").desc("Ejemplo: subject= Asunto del correo")
				.numberOfArgs(2).argName("sub").build();

		Option optTo = Option.builder("to").longOpt("to").desc("Ejemplo: to=imarin@domusateknik.com").numberOfArgs(2)
				.argName("to").build();

		Option optTemplate = Option.builder("template").longOpt("template").desc("creararticulo").numberOfArgs(2)
				.argName("template").build();

		Option optDesc = Option.builder("desc").longOpt("desc").desc("Se ha modificado").numberOfArgs(2).argName("desc")
				.build();

		Option optCod = Option.builder("cod").longOpt("cod").desc("Se ha modificado").numberOfArgs(2).argName("cod")
				.build();

		Option optItemId = Option.builder("itemId").longOpt("itemId").desc("Se ha modificado").numberOfArgs(2)
				.argName("itemId").build();

		Option optRevId = Option.builder("revId").longOpt("revId").desc("Se ha modificado").numberOfArgs(2)
				.argName("revId").build();

		options.addOption(optAccion);
		options.addOption(optUser);
		options.addOption(optFrom);
		options.addOption(optSubject);
		options.addOption(optTo);
		options.addOption(optTemplate);
		options.addOption(optDesc);
		options.addOption(optCod);
		options.addOption(optItemId);
		options.addOption(optRevId);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("accion") && cmd.hasOption("to") && cmd.hasOption("from") && cmd.hasOption("sub")
					&& cmd.hasOption("user") && cmd.hasOption("template") && cmd.hasOption("cod")
					&& cmd.hasOption("revId") && cmd.hasOption("itemId")) {
				cmdOK = true;

			} else {
				formatter.printHelp(APP_NAME, options);
				logger.severe("Falta parametros.");
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {

			String getUrl = "";

			String accion = cmd.getOptionValue("accion").toLowerCase();
			String user = cmd.getOptionValue("user");
			String from = cmd.getOptionValue("from").toLowerCase();
			String subject = cmd.getOptionValue("sub");
			String to = cmd.getOptionValue("to").toLowerCase();
			String template = cmd.getOptionValue("template");
			String desc = cmd.getOptionValue("desc");
			String cod = cmd.getOptionValue("cod");
			String itemid = cmd.getOptionValue("itemId");
			String revid = cmd.getOptionValue("revId");

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("user=" + user);
			logger.info("from=" + from);
			logger.info("subject=" + subject);
			logger.info("to=" + to);
			logger.info("template=" + template);
			logger.info("descripcion=" + desc);
			logger.info("codigo=" + cod);
			logger.info("itemid=" + itemid);
			logger.info("revid=" + revid);
			logger.info("******************");

			// FORMATO GET
			/*
			 * getUrl = resource + accion + "?to=" + to + "&from=" + from + "&subject=" +
			 * subject + "&body=" + body + "&user=" + user;
			 * 
			 * enviarDatosServidor(getUrl);
			 */

			getUrl = resource + accion;

			JSONObject json = new JSONObject();

			json.put("user", user);
			json.put("from", from);
			json.put("subject", subject);
			json.put("to", to);
			json.put("template", template);
			json.put("desc", desc);
			json.put("codigo", cod);
			json.put("itemId", itemid);
			json.put("revId", revid);

			String parameter = json.toJSONString();

			enviarDatosServidorPost(getUrl, parameter);

		}
		logger.info("Accion Enviar correo Finalizar.");

	}

	public static void accionImportarEstructura(Option optAccion, String[] args) {
		logger.info("Accion Importar Estructura Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		Option optItemId = Option.builder("item").longOpt("itemID").desc("Ejemplo: item=019331").numberOfArgs(2)
				.argName("item").build();

		Option optRevId = Option.builder("rev").longOpt("revID").desc("Ejemplo: rev=00").numberOfArgs(2).argName("rev")
				.build();

		options.addOption(optAccion);
		options.addOption(optCodigo);
		options.addOption(optItemId);
		options.addOption(optRevId);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("accion") && cmd.hasOption("cod") && cmd.hasOption("item") && cmd.hasOption("rev")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = cmd.getOptionValue("accion").toLowerCase();
			String codigo = cmd.getOptionValue("cod").toUpperCase();
			String itemId = cmd.getOptionValue("item").toUpperCase();
			String revId = cmd.getOptionValue("rev").toUpperCase();

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("codigo=" + codigo);
			logger.info("itemId=" + itemId);
			logger.info("revId=" + revId);
			logger.info("******************");

			// UriBuilder builder =
			// UriBuilder.fromUri(resource).path("{accion}").queryParam("codigo", codigo);
			// UriBuilder builder =
			// UriBuilder.fromUri(resource).path("{accion}").path("{codigo}");

			UriBuilder builder = UriBuilder.fromUri(resource).path("{accion}").queryParam("codigo", codigo)
					.queryParam("itemid", itemId).queryParam("revid", revId);
			URI uri = builder.build(accion);

			enviarDatosServidor(uri.toString());

		}
		logger.info("Accion Importar Estructura Finalizar.");
	}

	public static void accionRenombrarArchivo(Option optAccion, String[] args) {
		logger.info("Accion Renombrar Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optFilename = Option.builder("filename").longOpt("filename").desc("Ejemplo: filename=articulo")
				.numberOfArgs(2).argName("filename").build();

		Option optDestFilename = Option.builder("destfilename").longOpt("destfilename")
				.desc("Ejemplo: destfilename=SCOB000001").numberOfArgs(2).argName("desfilename").build();

		Option optpathorigen = Option.builder("pathorigen").longOpt("pathorigen").desc("Ejemplo: pathorigen=\\atenea\\")
				.numberOfArgs(2).argName("pathorigen").build();

		Option optpathdestino = Option.builder("pathdestino").longOpt("pathdestino")
				.desc("Ejemplo: destfilename=SCOB000001").numberOfArgs(2).argName("pathdestino").build();

		Option optextension = Option.builder("extension").longOpt("extension").desc("Ejemplo: entension")
				.numberOfArgs(2).argName("extension").build();

		options.addOption(optAccion);
		options.addOption(optFilename);
		options.addOption(optDestFilename);
		options.addOption(optpathorigen);
		options.addOption(optpathdestino);

		options.addOption(optextension);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("filename") && cmd.hasOption("destfilename") && cmd.hasOption("extension")
					&& cmd.hasOption("pathorigen") && cmd.hasOption("pathdestino")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = cmd.getOptionValue("accion").toLowerCase();
			String filename = cmd.getOptionValue("filename").toUpperCase();
			String destfilename = cmd.getOptionValue("destfilename").toUpperCase();
			String pathorigen = cmd.getOptionValue("pathorigen").toUpperCase();
			String pathdestino = cmd.getOptionValue("pathdestino").toUpperCase();
			String extension = cmd.getOptionValue("extension").toLowerCase();

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("filename=" + filename);
			logger.info("destfilename=" + destfilename);
			logger.info("pathorigen=" + pathorigen);
			logger.info("pathdestino=" + pathdestino);
			logger.info("extension=" + extension);
			logger.info("**********************");

			UriBuilder builder = UriBuilder.fromUri(resource).path("{accion}").queryParam("filename", filename)
					.queryParam("destfilename", destfilename).queryParam("pathorigen", pathorigen)
					.queryParam("pathdestino", pathdestino).queryParam("extension", extension);

			URI uri = builder.build(accion);

			enviarDatosServidor(uri.toString());

		}
		logger.info("Accion Renombrar Finalizar.");

	}

	public static void accionSbolPdf(Option optAccion, String[] args) {
		logger.info("Accion sboldpf Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optDestFilename = Option.builder("destfilename").longOpt("destfilename")
				.desc("Ejemplo: destfilename=SCOB000001").numberOfArgs(2).argName("desfilename").build();

		Option optpathdestino = Option.builder("pathdestino").longOpt("pathdestino")
				.desc("Ejemplo: destfilename=SCOB000001").numberOfArgs(2).argName("pathdestino").build();

		Option optextension = Option.builder("extension").longOpt("extension").desc("Ejemplo: entension")
				.numberOfArgs(2).argName("extension").build();

		options.addOption(optAccion);
		options.addOption(optDestFilename);
		options.addOption(optpathdestino);

		options.addOption(optextension);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("destfilename") && cmd.hasOption("extension") && cmd.hasOption("pathdestino")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = cmd.getOptionValue("accion").toLowerCase();
			String destfilename = cmd.getOptionValue("destfilename").toUpperCase();
			String pathdestino = cmd.getOptionValue("pathdestino").toUpperCase();
			String extension = cmd.getOptionValue("extension").toLowerCase();

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("destfilename=" + destfilename);
			logger.info("pathdestino=" + pathdestino);
			logger.info("extension=" + extension);
			logger.info("**********************");

			UriBuilder builder = UriBuilder.fromUri(resource).path("{accion}").queryParam("destfilename", destfilename)
					.queryParam("pathdestino", pathdestino).queryParam("extension", extension);

			URI uri = builder.build(accion);

			enviarDatosServidor(uri.toString());

		}
		logger.info("Accion sbolpdf Finalizar.");

	}

	public static void accionRenombrar(Option optAccion, String[] args) {
		logger.info("Accion Renombrar Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optFilename = Option.builder("filename").longOpt("filename").desc("Ejemplo: filename=articulo")
				.numberOfArgs(2).argName("filename").build();

		Option optDestFilename = Option.builder("destfilename").longOpt("destfilename")
				.desc("Ejemplo: destfilename=SCOB000001").numberOfArgs(2).argName("desfilename").build();

		Option optDirectory = Option.builder("directory").longOpt("directory").desc("Ejemplo: directory=articulo")
				.numberOfArgs(2).argName("desfilename").build();

		options.addOption(optAccion);
		options.addOption(optFilename);
		options.addOption(optDestFilename);
		options.addOption(optDirectory);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("filename") && cmd.hasOption("destfilename") && cmd.hasOption("directory")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = cmd.getOptionValue("accion").toLowerCase();
			String filename = cmd.getOptionValue("filename").toUpperCase();
			String destfilename = cmd.getOptionValue("destfilename").toUpperCase();
			String directory = cmd.getOptionValue("directory").toLowerCase();

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("filename=" + filename);
			logger.info("destfilename=" + destfilename);
			logger.info("directory=" + directory);
			logger.info("**********************");

			UriBuilder builder = UriBuilder.fromUri(resource).path("{accion}").queryParam("filename", filename)
					.queryParam("destfilename", destfilename).queryParam("directory", directory);

			URI uri = builder.build(accion);

			enviarDatosServidor(uri.toString());

		}
		logger.info("Accion Renombrar Finalizar.");

	}

	public static void accionTipo(Option optAccion, String[] args, String usuarioAudi) {
		logger.info("Accion Cambiar Tipo Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optTipo = Option.builder("tipo").longOpt("tipo").desc("Ejemplo: tipo=07").numberOfArgs(2).argName("tipo")
				.build();

		Option optUsuario = Option.builder("usuario").longOpt("usuario").desc("Ejemplo: usuario=Gurutze Agirre GAG")
				.numberOfArgs(2).argName("usuario").build();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		options.addOption(optAccion);
		options.addOption(optTipo);
		options.addOption(optUsuario);
		options.addOption(optCodigo);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("accion") && cmd.hasOption("tipo")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = cmd.getOptionValue("accion").toLowerCase();
			String tipo = cmd.getOptionValue("tipo").toLowerCase();

			String usuario = "";
			if (cmd.hasOption("usuario")) {
				usuario = cmd.getOptionValue("usuario").toUpperCase();
			} else {
				usuario = usuarioAudi.toUpperCase();
			}

			logger.info("Acccion=" + cmd.getOptionValue("accion"));
			logger.info("Tipo=" + cmd.getOptionValue("tipo"));
			logger.info("Usuario=" + usuario);

			String codigo = "";
			try {
				codigo = cmd.getOptionValue("codigo").toLowerCase();
			} catch (Exception e) {
			}
			logger.info("Codigo=" + codigo);

			String getUrl = "";

			if (accion.equals("tipo")) {
				getUrl = resource + accion + "?tipo=" + tipo + "&cod=" + codigo;

			}

			enviarDatosServidor(getUrl);
		}

		logger.info("Accion Cambar Tipo Finalizar.");
	}

	public static void accion(Option optAccion, String[] args, String usuarioAudi) {
		logger.info("Accion Compatible Empezar.");
		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optEstado = Option.builder("estado").longOpt("estado").desc("Ejemplo: estado=validar").numberOfArgs(2)
				.argName("estado").build();

		Option optUsuario = Option.builder("usuario").longOpt("usuario").desc("Ejemplo: usuario=Gurutze Agirre GAG")
				.numberOfArgs(2).argName("usuario").build();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		options.addOption(optAccion);
		options.addOption(optEstado);
		options.addOption(optUsuario);
		options.addOption(optCodigo);

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
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = cmd.getOptionValue("accion").toLowerCase();
			String estado = cmd.getOptionValue("estado").toLowerCase();

			String usuario = "";
			if (cmd.hasOption("usuario")) {
				usuario = cmd.getOptionValue("usuario").toUpperCase();
			} else {
				usuario = usuarioAudi.toUpperCase();
			}

			logger.info("Acccion=" + cmd.getOptionValue("accion"));
			logger.info("Estado=" + cmd.getOptionValue("estado"));
			logger.info("Usuario=" + usuario);

			String codigo = "";
			try {
				codigo = cmd.getOptionValue("codigo").toLowerCase();
			} catch (Exception e) {
			}
			logger.info("Codigo=" + codigo);

			String getUrl = "";

			if (accion.equals("estado") || accion.equals("articulo") || accion.equals("estructura")) {
				if (usuario == null || usuario.length() == 0) {
					getUrl = resource + accion + "?fase=" + estado + "&file=" + codigo;
				} else {
					getUrl = resource + accion + "?fase=" + estado + "&file=" + codigo + "&usuario=" + usuario;
				}
			} else {
				if (usuario == null || usuario.length() == 0) {
					getUrl = resource + accion + "?fase=" + estado + "&file=" + accion;
				} else {
					getUrl = resource + accion + "?fase=" + estado + "&file=" + accion + "&usuario=" + usuario;
				}
			}

			enviarDatosServidor(getUrl);
		}

		logger.info("Accion Compatible Finalizar.");
	}

	public static void main(String[] args) {

		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
		FileHandler fh;

		try {
			// This block configure the logger with handler and formatter
			fh = new FileHandler("c:\\temp\\interface.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Option optAccion = Option.builder("accion").longOpt("accion").desc(
				"Ejemplo: accion=articulo,accion=estructura, accion=estado, accion=renombrar o accion=correo o accion=ImportarEstructura")
				.numberOfArgs(2).argName("accion").build();

		HelpFormatter formatter = new HelpFormatter();
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Options options = new Options();

		options.addOption(optAccion);

		logger.info("----Argumentos-----------");
		for (String argv : args) {
			logger.info(argv.toString());
		}
		logger.info("----Fin Argumentos-----------");

		String usuarioAudi = "TC_" + System.getProperty("user.name").toUpperCase();

		logger.info("USUARIO EJECUTAR APP=" + usuarioAudi);

		try {
			cmd = parser.parse(options, args, true);

			if (cmd.hasOption("accion")) {
				String accion = cmd.getOptionValue("accion").toLowerCase();
				logger.info("Accion=" + accion);
				if (accion.startsWith("correocreararticulo")) {
					accionCorreoCreacionArticulo(optAccion, args);
				} else if (accion.startsWith("correomodificararticulo")) {
					accionCorreoModificarArticulo(optAccion, args);
				} else if (accion.startsWith("correoplantilla")) {
					accionCorreoPlantillaCondicion(optAccion, args);
				} else if (accion.startsWith("correo")) {
					accionCorreoTexto(optAccion, args);
				} else if (accion.startsWith("compararestructura")) {
					accionCompararEstructura(optAccion, args);
				} else if (accion.startsWith("importarestructura")) {
					accionImportarEstructura(optAccion, args);
				} else if (accion.startsWith("tipo")) {
					accionTipo(optAccion, args, usuarioAudi);
				} else if (accion.startsWith("renombrararchivo")) {
					accionRenombrarArchivo(optAccion, args);
				} else if (accion.startsWith("renombrar")) {
					accionRenombrar(optAccion, args);
				} else if (accion.startsWith("estadover")) {
					accionEstadoVer(optAccion, args);
				} else if (accion.startsWith("estadocomprobar")) {
					accionEstadoComprobar(optAccion, args);
				} else if (accion.startsWith("crearplano")) {
					acccionCrearPlano(optAccion, args);
				} else if (accion.startsWith("crearitra")) {
					acccionCrearItra(optAccion, args);
				} else if (accion.startsWith("estructuraitra")) {
					acccionEstructuraItra(optAccion, args);
				} else if (accion.startsWith("documentoborrar")) {
					acccionDocumentoBorrar(optAccion, args);
				} else if (accion.startsWith("sbolpdf")) {
					accionSbolPdf(optAccion, args);
				} else {
					accion(optAccion, args, usuarioAudi);
				}

			} else {
				logger.info("Falta parametro accion ejecutable.");
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			formatter.printHelp(APP_NAME, options);
		}

		System.exit(0);
	}

	private static void acccionDocumentoBorrar(Option optAccion, String[] args) {

		logger.info("Accion DocumentoBorrar Iniciar.");

		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		Option optUsuario = Option.builder("usuario").longOpt("usuario").desc("Ejemplo: usuario=Gurutze Agirre GAG")
				.numberOfArgs(2).argName("usuario").build();

		options.addOption(optAccion);
		options.addOption(optCodigo);
		options.addOption(optUsuario);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("codigo")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = "documentoborrar";
			String codigo = cmd.getOptionValue("codigo").toUpperCase();
			String usuario = cmd.getOptionValue("usuario");

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("codigo=" + codigo);
			logger.info("usuario=" + usuario);
			logger.info("**********************");

			String getUrl = "";

			UriBuilder builder = UriBuilder.fromUri(resource).path("{accion}").queryParam("file", codigo)
					.queryParam("usuario", usuario);

			URI uri = builder.build(accion);

			// openURL(getUrl);
			recogerDatosServidor(uri.toString());

		}
		logger.info("Accion Documento Borrar Finalizar.");

	}

	private static void acccionCrearPlano(Option optAccion, String[] args) {

		logger.info("Accion Crear Plano Iniciar.");

		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		Option optTipoDocumento = Option.builder("tipodocumento").longOpt("tipodocumento")
				.desc("Ejemplo: tipodocumento=01").numberOfArgs(2).argName("tipodocumento").build();

		Option optruta = Option.builder("ruta").longOpt("ruta").desc("Ejemplo: ruta=").numberOfArgs(2).argName("ruta")
				.build();

		Option optUsuario = Option.builder("usuario").longOpt("usuario").desc("Ejemplo: usuario=Gurutze Agirre GAG")
				.numberOfArgs(2).argName("usuario").build();

		options.addOption(optAccion);
		options.addOption(optCodigo);
		options.addOption(optTipoDocumento);
		options.addOption(optruta);
		options.addOption(optUsuario);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("codigo") && cmd.hasOption("tipodocumento") && cmd.hasOption("ruta")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = "documentoplano";
			String codigo = cmd.getOptionValue("codigo").toUpperCase();
			String tipodocumento = cmd.getOptionValue("tipodocumento");
			String ruta = cmd.getOptionValue("ruta");
			String usuario = cmd.getOptionValue("usuario");

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("codigo=" + codigo);
			logger.info("tipodocumento=" + tipodocumento);
			logger.info("ruta=" + ruta);
			logger.info("usuario=" + usuario);
			logger.info("**********************");

			String getUrl = "";

			UriBuilder builder = UriBuilder.fromUri(resource).path("{accion}").queryParam("file", codigo)
					.queryParam("tipoDocumento", tipodocumento).queryParam("ruta", ruta).queryParam("usuario", usuario);

			URI uri = builder.build(accion);

			// openURL(getUrl);
			recogerDatosServidor(uri.toString());

		}
		logger.info("Accion Crear Plano Finalizar.");
	}

	private static void acccionCrearItra(Option optAccion, String[] args) {

		logger.info("Accion Crear Itra Iniciar.");

		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		Option optTipoDocumento = Option.builder("tipodocumento").longOpt("tipodocumento")
				.desc("Ejemplo: tipodocumento=01").numberOfArgs(2).argName("tipodocumento").build();

		Option optruta = Option.builder("ruta").longOpt("ruta").desc("Ejemplo: ruta=").numberOfArgs(2).argName("ruta")
				.build();

		Option optUsuario = Option.builder("usuario").longOpt("usuario").desc("Ejemplo: usuario=Gurutze Agirre GAG")
				.numberOfArgs(2).argName("usuario").build();

		options.addOption(optAccion);
		options.addOption(optCodigo);
		options.addOption(optTipoDocumento);
		options.addOption(optruta);
		options.addOption(optUsuario);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("codigo") && cmd.hasOption("tipodocumento") && cmd.hasOption("ruta")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = "documentoplano";
			String codigo = cmd.getOptionValue("codigo").toUpperCase();
			String tipodocumento = cmd.getOptionValue("tipodocumento");
			String ruta = cmd.getOptionValue("ruta");
			String usuario = cmd.getOptionValue("usuario");

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("codigo=" + codigo);
			logger.info("tipodocumento=" + tipodocumento);
			logger.info("ruta=" + ruta);
			logger.info("usuario=" + usuario);
			logger.info("**********************");

			String getUrl = "";

			UriBuilder builder = UriBuilder.fromUri(resource).path("{accion}").queryParam("file", codigo)
					.queryParam("tipoDocumento", tipodocumento).queryParam("ruta", ruta).queryParam("usuario", usuario);

			URI uri = builder.build(accion);

			// openURL(getUrl);
			recogerDatosServidor(uri.toString());

		}
		logger.info("Accion Crear Itra Finalizar.");
	}

	private static void acccionEstructuraItra(Option optAccion, String[] args) {

		logger.info("Accion EstructuraItra Iniciar.");

		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		Option optTipoDocumento = Option.builder("tipodocumento").longOpt("tipodocumento")
				.desc("Ejemplo: tipodocumento=01").numberOfArgs(2).argName("tipodocumento").build();

		Option optUsuario = Option.builder("usuario").longOpt("usuario").desc("Ejemplo: usuario=Gurutze Agirre GAG")
				.numberOfArgs(2).argName("usuario").build();

		options.addOption(optAccion);
		options.addOption(optCodigo);
		options.addOption(optTipoDocumento);
		options.addOption(optUsuario);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("codigo") && cmd.hasOption("tipodocumento")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = "documentorelacionar";
			String codigo = cmd.getOptionValue("codigo").toUpperCase();
			String tipodocumento = cmd.getOptionValue("tipodocumento");
			String usuario = cmd.getOptionValue("usuario");

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("codigo=" + codigo);
			logger.info("tipodocumento=" + tipodocumento);
			logger.info("usuario=" + usuario);
			logger.info("**********************");

			String getUrl = "";

			UriBuilder builder = UriBuilder.fromUri(resource).path("{accion}").queryParam("file", codigo)
					.queryParam("tipoDocumento", tipodocumento).queryParam("usuario", usuario);

			URI uri = builder.build(accion);

			// openURL(getUrl);
			recogerDatosServidor(uri.toString());

		}
		logger.info("Accion EstructuraItra Finalizar.");
	}

	private static void accionEstadoVer(Option optAccion, String[] args) {
		logger.info("Accion Estado Ver Enpezar.");

		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		options.addOption(optAccion);
		options.addOption(optCodigo);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("codigo")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = "estadoVer";
			String codigo = cmd.getOptionValue("codigo").toUpperCase();

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("codigo=" + codigo);
			logger.info("**********************");

			UriBuilder builder = UriBuilder.fromUri(resourceWeb).path("{accion}").path(codigo).path("comparar");

			URI uri = builder.build(accion);

			String getUrl = "";
			getUrl = resource + accion + "?codigo=" + codigo;

			// openURL(getUrl);
			recogerDatosServidor(getUrl);

		}
		logger.info("Accion Comparar Estructura Finalizar.");

	}

	private static void accionEstadoComprobar(Option optAccion, String[] args) {
		logger.info("Accion Estado Ver Enpezar.");

		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		Option optEstado = Option.builder("estado").longOpt("estado").desc("Ejemplo: estado=validar").numberOfArgs(2)
				.argName("estado").build();

		Option optUsuario = Option.builder("usuario").longOpt("usuario").desc("Ejemplo: usuario=Gurutze Agirre GAG")
				.numberOfArgs(2).argName("usuario").build();

		options.addOption(optAccion);
		options.addOption(optCodigo);
		options.addOption(optEstado);
		options.addOption(optUsuario);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("codigo") && cmd.hasOption("estado")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = "estadoComprobar";
			String codigo = cmd.getOptionValue("codigo").toUpperCase();
			String estado = cmd.getOptionValue("estado").toUpperCase();

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("codigo=" + codigo);
			logger.info("estado=" + estado);

			logger.info("**********************");

			UriBuilder builder = UriBuilder.fromUri(resourceWeb).path("{accion}").path(codigo).path("comparar");

			URI uri = builder.build(accion);

			String getUrl = "";
			getUrl = resource + accion + "?codigo=" + codigo + "&estado=" + estado;

			// openURL(getUrl);
			recogerDatosServidor(getUrl);

		}
		logger.info("Accion Comparar Estructura Finalizar.");

	}

	private static void accionCompararEstructura(Option optAccion, String[] args) {

		logger.info("Accion Comparar Estructura Empezar.");

		HelpFormatter formatter = new HelpFormatter();
		Options options = new Options();

		Option optCodigo = Option.builder("cod").longOpt("codigo").desc("Ejemplo: cod=SCON00000").numberOfArgs(2)
				.argName("cod").build();

		options.addOption(optAccion);
		options.addOption(optCodigo);

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		Boolean cmdOK = false;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("codigo")) {
				cmdOK = true;
			} else {
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			logger.severe("Error procesando parametros.");
			logger.severe(e1.toString());
			e1.printStackTrace();
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = "estructura";
			String codigo = cmd.getOptionValue("codigo").toUpperCase();

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("codigo=" + codigo);
			logger.info("******************");

			UriBuilder builder = UriBuilder.fromUri(resourceWeb).path("{accion}").path(codigo).path("comparar");

			URI uri = builder.build(accion);

			openURL(uri.toString());

		}
		logger.info("Accion Comparar Estructura Finalizar.");

	}

	private static void openURL(String url) {
		String osName = System.getProperty("os.name");
		try {
			if (osName.startsWith("Windows")) {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} else if (osName.startsWith("Mac OS X")) {
				// Runtime.getRuntime().exec("open -a safari " + url);
				// Runtime.getRuntime().exec("open " + url + "/index.html");
				Runtime.getRuntime().exec("open " + url);
			} else {
				System.out.println("Please open a browser and go to " + url);
			}
		} catch (IOException e) {
			System.out.println("Failed to start a browser to open the url " + url);
			e.printStackTrace();
		}
	}

	public static void recogerDatosServidor(String getUrl) {
		logger.info("Iniciado proceso get EnviarDatosServidor");
		logger.info("Connectando ->" + getUrl);
		WebResource webResource = client.resource(getUrl);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		logger.info("Http Status  " + response.getStatus());
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);

		if (result.equals("false")) {
			String error = getUrl + " no validado.";
			logger.severe(error);
			throw new RuntimeException(error);
		} else {
			System.out.println(result);
			logger.info(getUrl + " validado.");
		}

		System.out.println("Response from the Server: " + result);
	}

	public static void enviarDatosServidor(String getUrl) {
		logger.info("Iniciado proceso get EnviarDatosServidor");
		logger.info("Connectando ->" + getUrl);
		WebResource webResource = client.resource(getUrl);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		logger.info("Http Status  " + response.getStatus());
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);

		if (result.equals("false")) {
			String error = getUrl + " no validado.";
			logger.severe(error);
			throw new RuntimeException(error);
		} else {
			logger.info(getUrl + " validado.");
		}

		System.out.println("Response from the Server: " + result);
	}

	public static void enviarDatosServidorPost(String getUrl, String parameter) {
		logger.info("Iniciado proceso post EnviarDatosServidor");
		logger.info("Connectando ->" + getUrl);

		WebResource webResource = client.resource(getUrl);
		ClientResponse response = webResource.accept("application/json").type("application/json")
				.post(ClientResponse.class, parameter);

		logger.info("Http Status  " + response.getStatus());
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}

		String result = response.getEntity(String.class);

		if (result.equals("false")) {
			String error = getUrl + " no validado.";
			logger.severe(error);
			throw new RuntimeException(error);
		} else {
			logger.info(getUrl + " validado.");
		}

		System.out.println("Response from the Server: " + result);
	}
}
