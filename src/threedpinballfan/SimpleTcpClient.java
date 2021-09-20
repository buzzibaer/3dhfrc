package threedpinballfan;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * 
 * @author buzzibaer on github = https://github.com/buzzibaer
 *
 */
public class SimpleTcpClient {

	/**
	 * IP address of the FAN
	 * the fan is exposing its on wifi without crypto
	 * it has always the same IP
	 */
	public static final String DEFUALT_SERVER_IP = "192.168.4.1";
	
	/**
	 * Port to communicate with the fan
	 * This is the Port on the fan, not on your client :)
	 */
	public static final int DEFUALT_SERVER_PORT = 5233;

	
	private String serverIP;
	private int serverPort;
	private PrintWriter printWriter;
	
	private DataInputStream dataInputStream;
	private Socket socket = null;
	public boolean connState = false;
	public SimpleTcpClient() {
		this.serverIP = "192.168.4.1";
		this.serverPort = DEFUALT_SERVER_PORT;
	}

	
	/**
	 * Method sends a specific command to the fan
	 * this is a technical send method
	 * just communication purposes
	 * @param str a string representing the command for the fan
	 */
	public void send(final String str) {

		try {
			if (!(str.isEmpty())) {
				if (SimpleTcpClient.this.printWriter != null) {
					SimpleTcpClient.this.printWriter.print(str);
					SimpleTcpClient.this.printWriter.flush();


//					String response = SimpleTcpClient.this.dataInputStream.readUTF();
//					System.out.println("inputstream = " + response);
					
					return;
				}
			}
//                    TcpClient.this.onFailResult("fail");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	};
	
	/**
	 * method will try to establish communication to the fan
	 */
	public void connSocket() {
        try {
            if (this.printWriter != null) {
                this.printWriter.close();
                this.printWriter = null;
            }
            if (this.dataInputStream != null) {
                this.dataInputStream.close();
                this.dataInputStream = null;
            }
            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
            this.socket = new Socket(this.serverIP, this.serverPort);
            this.socket.setSoTimeout(50000);
            this.socket.setTcpNoDelay(true);
//            this.socket.setKeepAlive(true);
            this.socket.setKeepAlive(false);
            this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            if (this.socket.isConnected()) {
                this.connState = true;
                return;
            }
            this.connState = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * closing communication to the fan
	 * @throws IOException
	 */
	public void deleteSocket() throws IOException {
		this.socket.close();
	}
}
