package threedpinballfan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * Lauch class for selection of a specific video / picture on a 3d holografic
 * fan
 * 
 * @author buzzibaer
 *
 */
public class Lauch {

	static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	static SimpleTcpClient client;
	static Fan fan;

	/**
	 * CommandLine starting method
	 * 
	 * @param args expects the tablename from commandline from pinupopper or other
	 *             system for choosing the right video / pic
	 * @throws InterruptedException
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, SecurityException, IOException {

		String gamename = "";

		for (int i = 0; i < args.length; i++) {
			gamename = gamename + args[i];
			if (i < args.length) {
				gamename = gamename + " ";
			}
		}

		gamename = gamename.trim();

//		int exitCode = new CommandLine(new fancommands()).execute(args);
//		System.exit(exitCode);

		// Reading config file
		java.util.Properties props = readPropertiesFile();
		initLogger(props);

		// Printing CommandLine Parameters
		logger.info("Startparameter =  " + gamename);
		logger.info("Properties = " + props);
		int videoid = Integer.parseInt(props.getProperty(gamename, "1"));

		// init TCP connection
		client = initTcpClient();
		fan = new Fan(logger, client);

		// single playback mode selection
		fan.selectSingePlaybackMode();

		// select pic / video
		logger.info("selcted game is >" + gamename + "< with id from mapping = >" + videoid + "<");
		Fan.selectVidForTable(videoid);

		client.deleteSocket();

		logger.info("Exit");
		System.exit(0);
	}

	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static java.util.Properties readPropertiesFile() throws FileNotFoundException, IOException {

		java.util.Properties props = new java.util.Properties();

		File myObj = new File("tabletovideomapping.ini");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			if (!data.startsWith("#")) {
				int delimeterPos = data.lastIndexOf("=");
				String key = data.substring(0, delimeterPos);
				String value = data.substring(delimeterPos + 1);

				System.out.println("Key = " + key + " Value = " + value);

				props.setProperty(key, value);

			}
		}
		myReader.close();

//		java.io.FileInputStream fis = new java.io.FileInputStream(new java.io.File("tabletovideomapping.ini"));
//		props.load(fis);
//		fis.close();
		return props;
	}

	/**
	 * Logger and level initiation
	 * 
	 * @throws IOException
	 */
	private static void initLogger(Properties probs) throws IOException {

		Level level;

		try {
			level = Level.parse(probs.getProperty("loglevel"));
		} catch (IllegalArgumentException e) {
			// defaults to ALL
			// default
			level = Level.ALL;
		}

		logger.setLevel(level);

		String logHandler = probs.getProperty("loghandler", "file");

		if (logHandler.equalsIgnoreCase("file")) {
			Handler fileHandler = new FileHandler("fan.log");
			fileHandler.setLevel(level);
			logger.addHandler(fileHandler);
		} else if (logHandler.equalsIgnoreCase("console")) {
			Handler consolehandler = new ConsoleHandler();
			consolehandler.setLevel(level);
			logger.addHandler(consolehandler);
		} else if (logHandler.equalsIgnoreCase("both")) {
			Handler consolehandler = new ConsoleHandler();
			consolehandler.setLevel(level);
			logger.addHandler(consolehandler);
			Handler fileHandler = new FileHandler("fan.log");
			fileHandler.setLevel(level);
			logger.addHandler(fileHandler);
		}

		logger.info("Starte");

	}

	/**
	 * Opening a TCP channel to the WiFi Module on the 3D Fan
	 * 
	 * @return
	 */
	private static SimpleTcpClient initTcpClient() {
		SimpleTcpClient client = new SimpleTcpClient();
		logger.info("TCPClient created");
		client.connSocket();
		logger.info("Socket open");
		return client;
	}

	/**
	 * Load a Properties File
	 * 
	 * @param propsFile
	 * @return Properties
	 * @throws IOException
	 */
	public static Properties load(File propsFile) throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(propsFile);
		props.load(fis);
		fis.close();
		return props;
	}
}
