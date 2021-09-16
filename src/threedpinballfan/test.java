package threedpinballfan;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {

	public static void main(String[] args) throws InterruptedException, SecurityException, IOException {

		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);
		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);

		Handler fileHandler = new FileHandler("fan.log");
		fileHandler.setLevel(Level.ALL);

		logger.addHandler(fileHandler);

		logger.info("Starte");

		logger.info("Startparameter =  " + args[0]);

		String playNext = UpLoadData.playNext();
		String getFileList = UpLoadData.getFileList();
		String playModelSingle = UpLoadData.playModelSingle();
		String playFile4 = UpLoadData.playFile(4);
		String playLoop = UpLoadData.playModelLoop();

		TcpClient client = new TcpClient();
		logger.info("TCPClient created");
		client.connSocket();
		logger.info("Socket open");

//		  client.send(getFileList);
//		  logger.info("Command Send = getFileList");

//		  client.send(playNext);
//		  logger.info("Command Send = playnext > " + playNext);

		client.send(playModelSingle);
		logger.info("Command Send = playModelSingle > " + playModelSingle);

//		  Thread.sleep(30000);

//		  client.send(playLoop);
//		  logger.info("Command Send = playLoop > " + playLoop);

		if (args[0].equalsIgnoreCase("Darkprincess")) {
			client.send(playFile4);
			logger.info("DarkPrincess detected!");
			logger.info("Command Send = playFile > " + playFile4);
		}

//		  client.send(playLoop);
//		  logger.info("Command Send = playLoop > " + playLoop);

		client.send(playModelSingle);
		logger.info("Command Send = playModelSingle > " + playModelSingle);

		logger.info("Exit");
		System.exit(0);
	}
}
