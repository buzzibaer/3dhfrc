package threedpinballfan;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * Lauch class for selection of a specific video / picture on a 3d holografic fan
 * @author buzzibaer
 *
 */
public class Lauch {
	
	static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * CommandLine starting method
	 * @param args expects the tablename from commandline from pinupopper or other system for choosing the right video / pic
	 * @throws InterruptedException
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, SecurityException, IOException {

		initLogger();
		
		// Printing CommandLine Parameters
		logger.info("Startparameter =  " + args[0]);


		


		TcpClient client = initTcpClient();


		fanSelectSingePlaybackMode(client);


		fanSelectVidForTable(args, client);




		logger.info("Exit");
		System.exit(0);
	}

	/**
	 * Logger and level initiation
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
	 * Selection of specific table on the wifi fan
	 * @param args
	 * @param client
	 */
	private static void fanSelectVidForTable(String[] args, TcpClient client) {
		String playFile4 = UpLoadData.playFile(4);
		if (args[0].equalsIgnoreCase("Darkprincess")) {
			client.send(playFile4);
			logger.info("DarkPrincess detected!");
			logger.info("Command Send = playFile > " + playFile4);
		}
	}

	/**
	 * Setting 3d wifi fan to single vid play mode
	 * the fan usually rotates / cylces to all videos on the SD card, we wannt to have it only in playback mode for a singe file
	 * @param client
	 */
	private static void fanSelectSingePlaybackMode(TcpClient client) {
		String playModelSingle = UpLoadData.playModelSingle();
		client.send(playModelSingle);
		logger.info("Command Send = playModelSingle > " + playModelSingle);
	}

	/**
	 * Opening a TCP channel to the WiFi Module on the 3D Fan
	 * @return
	 */
	private static TcpClient initTcpClient() {
		TcpClient client = new TcpClient();
		logger.info("TCPClient created");
		client.connSocket();
		logger.info("Socket open");
		return client;
	}
}
