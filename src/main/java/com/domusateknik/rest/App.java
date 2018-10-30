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

	public static void accionCorreo(Option optAccion, String[] args) {
		logger.info("Accion Enviar correo Empezar.");
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

		options.addOption(optAccion);
		options.addOption(optTo);
		options.addOption(optFrom);
		options.addOption(optSubject);
		options.addOption(optUser);
		options.addOption(optBody);

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
			logger.severe("Falta parametros.");
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

			logger.info("*****PARAMETROS*******");
			logger.info("accion=" + accion);
			logger.info("to=" + to);
			logger.info("from=" + from);
			logger.info("subject=" + subject);
			logger.info("body=" + body);
			logger.info("user=" + user);
			logger.info("******************");

			getUrl = resource + accion + "?to=" + to + "&from=" + from + "@subject=" + subject + "&body=" + body
					+ "&user=" + user;

			enviarDatosServidor(getUrl);

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
			formatter.printHelp(APP_NAME, options);
		}

		if (cmdOK) {
			String accion = cmd.getOptionValue("accion").toLowerCase();
			String codigo = cmd.getOptionValue("codigo").toUpperCase();
			String itemId = cmd.getOptionValue("itemId").toUpperCase();
			String revId = cmd.getOptionValue("revId").toUpperCase();

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

			logger.info("******************");

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
				if (accion.startsWith("correo")) {
					accionCorreo(optAccion, args);
				} else if (accion.startsWith("compararestructura")) {
					accionCompararEstructura(optAccion, args);
				} else if (accion.startsWith("importarestructura")) {
					accionImportarEstructura(optAccion, args);
				}

				else if (accion.startsWith("tipo")) {
					accionTipo(optAccion, args, usuarioAudi);
				}

				else if (accion.startsWith("renombrar")) {
					accionRenombrar(optAccion, args);
				}

				else {
					accion(optAccion, args, usuarioAudi);
				}

			} else {
				logger.info("Falta parametro accion ejecutable.");
				formatter.printHelp(APP_NAME, options);
			}
		} catch (ParseException e1) {
			formatter.printHelp(APP_NAME, options);
		}

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

	public static void enviarDatosServidor(String getUrl) {
		logger.info("Iniciado proceso EnviarDatosServidor");
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
}
