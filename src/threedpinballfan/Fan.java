package threedpinballfan;

import java.util.logging.Logger;


/**
 * Fan classs represents the 
 * @author buzzibaer on github = https://github.com/buzzibaer
 *
 */
public class Fan {

	private static Logger logger;
	private static SimpleTcpClient client;

	public Fan(Logger logger, SimpleTcpClient client) {
		Fan.logger = logger;
		Fan.client = client;
	}

	/**
	 * Setting 3d wifi fan to single vid play mode the fan usually rotates / cylces
	 * to all videos on the SD card, we wannt to have it only in playback mode for a
	 * singe file
	 * 
	 * @param client
	 */
	public void selectSingePlaybackMode() {
		String playModelSingle = UpLoadData.playModelSingle();
		client.send(playModelSingle);
		logger.info("Command Send = playModelSingle > " + playModelSingle);
	}

	/**
	 * Selection of specific table on the wifi fan
	 * 
	 * @param args
	 * @param client
	 */
	public static void selectVidForTable(int videoid) {
		String playFile = UpLoadData.playFile(videoid);

		client.send(playFile);
		logger.info("Command Send = playFile > " + playFile);
	}
	
	/**
	 * method fetches the list of possible videos for playback from the fan
	 */
	public static void fetchVideoList  () {
		String fileList = UpLoadData.getFileList();

		client.send(fileList);
		logger.info("Command Send = getFileList > " + fileList);
		
	}

}
