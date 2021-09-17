package threedpinballfan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import picocli.CommandLine;

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
	static TcpClient client;
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

//		int exitCode = new CommandLine(new fancommands()).execute(args);
//		System.exit(exitCode);

		initLogger();

		// Printing CommandLine Parameters
		logger.info("Startparameter =  " + args[0]);

		// Reading config file
		java.util.Properties props = readPropertiesFile();
		logger.info("Properties = " + props);
		int videoid = Integer.parseInt( props.getProperty(args[0], "1"));
		logger.info("selcted video id from mapping = " + props);

		// init TCP connection
		client = initTcpClient();
		fan = new Fan(logger,client);

		// single playback mode selection
		fan.selectSingePlaybackMode();

		// select pic / video
		fan.selectVidForTable(videoid);

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
		java.io.FileInputStream fis = new java.io.FileInputStream(new java.io.File("tabletovideomapping.ini"));
		props.load(fis);
		fis.close();
		return props;
	}

	/**
	 * Logger and level initiation
	 * 
	 * @throws IOException
	 */
	private static void initLogger() throws IOException {

		logger.setLevel(Level.ALL);
		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);

		Handler fileHandler = new FileHandler("fan.log");
		fileHandler.setLevel(Level.ALL);

		logger.addHandler(fileHandler);

		logger.info("Starte");

	}




	/**
	 * Opening a TCP channel to the WiFi Module on the 3D Fan
	 * 
	 * @return
	 */
	private static TcpClient initTcpClient() {
		TcpClient client = new TcpClient();
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
