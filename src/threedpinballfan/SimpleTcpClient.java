package threedpinballfan;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleTcpClient {

	public static final String DEFUALT_SERVER_IP = "192.168.4.1";
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
	
	public void deleteSocket() throws IOException {
		this.socket.close();
	}
}
