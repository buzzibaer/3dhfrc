package threedpinballfan;


import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpClient implements Runnable {
    public static final String DEFUALT_SERVER_IP = "192.168.4.1";
    public static final int DEFUALT_SERVER_PORT = 5233;
    public static final String RESULT_FAIL = "fail";
    public static final String RESULT_SUC = "suc";
    public static final String SERVICE_DATA = "service_data";
    private String TAG = "TcpClient";
    private byte[] buff = new byte[1024];
    private byte[] buffVer = new byte[1];
    public boolean connState = false;
    private DataInputStream dataInputStream;
    private boolean isRun = true;
    String playList;
    /* access modifiers changed from: private */
    public PrintWriter printWriter;
    private int rcvLen;
//    private ResultListener resultListener;
    private String serverIP;
    private int serverPort;
    private Socket socket = null;
    private ExecutorService threadPool = Executors.newCachedThreadPool();
//
//    public interface ResultListener {
//        void onResult(String str, String str2);
//    }
//
//    public TcpClient(ResultListener resultListener2) {
//        this.resultListener = resultListener2;
//        this.serverIP = "192.168.4.1";
//        this.serverPort = DEFUALT_SERVER_PORT;
//    }

    
    public TcpClient() {
        this.serverIP = "192.168.4.1";
        this.serverPort = DEFUALT_SERVER_PORT;
    }
    
    public void send(final String str) {
        this.threadPool.execute(new Runnable() {
            public void run() {
                try {
                    if (!(str.isEmpty())) {
                        if (TcpClient.this.printWriter != null) {
                            TcpClient.this.printWriter.print(str);
                            TcpClient.this.printWriter.flush();
                            
//                            TcpClient.this.dataInputStream.read();
//                            String outputString = dataInputStream.toString();
//                            System.out.println("DEBUG OUTPUT:");
//                            System.out.println(outputString);
                            return;
                        }
                    }
//                    TcpClient.this.onFailResult("fail");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    /* access modifiers changed from: private */
//    public void onFailResult(String str) {
//        if (this.resultListener != null) {
//            this.resultListener.onResult("fail", str);
//        }
//    }
//
//    private void onSucResult(String str) {
//        if (this.resultListener != null) {
//            this.resultListener.onResult("suc", str);
//        }
//    }
//
//    private void onServiceData(String str) {
//        if (this.resultListener != null) {
//            this.resultListener.onResult("service_data", str);
//        }
//    }
//
//    public void startToEncodeVideo(String str) {
//        if (this.resultListener != null) {
//            this.resultListener.onResult(str, "");
//        }
//    }

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
            this.socket.setKeepAlive(true);
            this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            if (this.socket.isConnected()) {
                this.connState = true;
//                onSucResult("Socket opening = successfull");
                return;
            }
            this.connState = false;
//            onFailResult("Socket opening = failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:35|36|48) */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r15.connState = false;
        java.lang.Thread.sleep(3000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0160, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0161, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0157 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r15 = this;
        L_0x0000:
            boolean r0 = r15.isRun
            if (r0 == 0) goto L_0x0166
            boolean r0 = r15.connState
            r1 = 1
            if (r0 != 0) goto L_0x000e
            r15.connSocket()
            r15.connState = r1
        L_0x000e:
            r0 = 0
            java.io.DataInputStream r2 = r15.dataInputStream     // Catch:{ Exception -> 0x0157 }
            byte[] r3 = r15.buff     // Catch:{ Exception -> 0x0157 }
            int r2 = r2.read(r3)     // Catch:{ Exception -> 0x0157 }
            r15.rcvLen = r2     // Catch:{ Exception -> 0x0157 }
            int r2 = r15.rcvLen     // Catch:{ Exception -> 0x0157 }
            if (r2 <= 0) goto L_0x0153
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x0157 }
            byte[] r3 = r15.buff     // Catch:{ Exception -> 0x0157 }
            int r4 = r15.rcvLen     // Catch:{ Exception -> 0x0157 }
            java.lang.String r5 = "GB2312"
            r2.<init>(r3, r0, r4, r5)     // Catch:{ Exception -> 0x0157 }
            r15.playList = r2     // Catch:{ Exception -> 0x0157 }
            java.lang.String r2 = r15.playList     // Catch:{ Exception -> 0x0157 }
            java.lang.String r3 = "c30c42"
            boolean r2 = r2.startsWith(r3)     // Catch:{ Exception -> 0x0157 }
            if (r2 == 0) goto L_0x0044
            java.lang.String r2 = r15.playList     // Catch:{ Exception -> 0x0157 }
            java.lang.String r3 = "a4a8c2e3"
            boolean r2 = r2.endsWith(r3)     // Catch:{ Exception -> 0x0157 }
            if (r2 == 0) goto L_0x0044
            java.lang.String r1 = r15.playList     // Catch:{ Exception -> 0x0157 }
            r15.onServiceData(r1)     // Catch:{ Exception -> 0x0157 }
            goto L_0x0000
        L_0x0044:
            java.lang.String r2 = r15.playList     // Catch:{ Exception -> 0x0157 }
            java.lang.String r3 = "c30c38"
            boolean r2 = r2.startsWith(r3)     // Catch:{ Exception -> 0x0157 }
            if (r2 == 0) goto L_0x0000
            java.lang.String r2 = r15.playList     // Catch:{ Exception -> 0x0157 }
            java.lang.String r3 = "a4a8c2e3"
            boolean r2 = r2.endsWith(r3)     // Catch:{ Exception -> 0x0157 }
            if (r2 == 0) goto L_0x0000
            java.lang.String r2 = ""
            byte[] r3 = r15.buff     // Catch:{ Exception -> 0x0157 }
            r4 = 6
            byte r3 = r3[r4]     // Catch:{ Exception -> 0x0157 }
            int r3 = r3 + -97
            int r3 = r3 * 19
            int r3 = r3 * 17
            byte[] r5 = r15.buff     // Catch:{ Exception -> 0x0157 }
            r6 = 7
            byte r5 = r5[r6]     // Catch:{ Exception -> 0x0157 }
            int r5 = r5 + -98
            int r5 = r5 * 17
            int r3 = r3 + r5
            byte[] r5 = r15.buff     // Catch:{ Exception -> 0x0157 }
            r6 = 8
            byte r5 = r5[r6]     // Catch:{ Exception -> 0x0157 }
            int r3 = r3 + r5
            int r3 = r3 + -99
            int r3 = r3 - r4
            byte[] r5 = r15.buffVer     // Catch:{ Exception -> 0x0157 }
            byte[] r7 = r15.buff     // Catch:{ Exception -> 0x0157 }
            int r8 = r3 + 9
            r9 = 9
            int r8 = r8 + r9
            byte r7 = r7[r8]     // Catch:{ Exception -> 0x0157 }
            r5[r0] = r7     // Catch:{ Exception -> 0x0157 }
            java.lang.String r5 = new java.lang.String     // Catch:{ Exception -> 0x0157 }
            byte[] r7 = r15.buffVer     // Catch:{ Exception -> 0x0157 }
            r5.<init>(r7)     // Catch:{ Exception -> 0x0157 }
            java.lang.String r7 = "T"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x0157 }
            if (r5 == 0) goto L_0x009b
            r8 = r2
            r2 = 9
            r5 = 0
            r7 = 1
            goto L_0x00a0
        L_0x009b:
            r8 = r2
            r2 = 9
            r5 = 0
            r7 = 0
        L_0x00a0:
            int r10 = r2 + -9
            if (r10 >= r3) goto L_0x00f8
            byte[] r10 = r15.buff     // Catch:{ Exception -> 0x0157 }
            byte r10 = r10[r2]     // Catch:{ Exception -> 0x0157 }
            int r10 = r10 + -48
            int r10 = r10 * 10
            byte[] r11 = r15.buff     // Catch:{ Exception -> 0x0157 }
            int r12 = r2 + 1
            byte r11 = r11[r12]     // Catch:{ Exception -> 0x0157 }
            int r10 = r10 + r11
            int r10 = r10 + -48
            int r11 = r10 + 2
            int r5 = r5 + r11
            java.lang.String r11 = new java.lang.String     // Catch:{ Exception -> 0x0157 }
            byte[] r12 = r15.buff     // Catch:{ Exception -> 0x0157 }
            int r13 = r2 + 2
            java.lang.String r14 = "GB2312"
            r11.<init>(r12, r13, r10, r14)     // Catch:{ Exception -> 0x0157 }
            int r12 = r11.length()     // Catch:{ Exception -> 0x0157 }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ Exception -> 0x0157 }
            int r13 = r12.length()     // Catch:{ Exception -> 0x0157 }
            if (r13 != r1) goto L_0x00e2
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0157 }
            r13.<init>()     // Catch:{ Exception -> 0x0157 }
            java.lang.String r14 = "0"
            r13.append(r14)     // Catch:{ Exception -> 0x0157 }
            r13.append(r12)     // Catch:{ Exception -> 0x0157 }
            java.lang.String r12 = r13.toString()     // Catch:{ Exception -> 0x0157 }
        L_0x00e2:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0157 }
            r13.<init>()     // Catch:{ Exception -> 0x0157 }
            r13.append(r8)     // Catch:{ Exception -> 0x0157 }
            r13.append(r12)     // Catch:{ Exception -> 0x0157 }
            r13.append(r11)     // Catch:{ Exception -> 0x0157 }
            java.lang.String r8 = r13.toString()     // Catch:{ Exception -> 0x0157 }
            int r2 = r2 + r10
            int r2 = r2 + 2
            goto L_0x00a0
        L_0x00f8:
            if (r5 != r3) goto L_0x0000
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x0157 }
            byte[] r2 = r15.buff     // Catch:{ Exception -> 0x0157 }
            java.lang.String r3 = "ASCII"
            r1.<init>(r2, r0, r9, r3)     // Catch:{ Exception -> 0x0157 }
            if (r7 == 0) goto L_0x012e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0157 }
            r2.<init>()     // Catch:{ Exception -> 0x0157 }
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0157 }
            byte[] r5 = r15.buff     // Catch:{ Exception -> 0x0157 }
            int r7 = r15.rcvLen     // Catch:{ Exception -> 0x0157 }
            int r7 = r7 + -25
            java.lang.String r9 = "ASCII"
            r3.<init>(r5, r7, r4, r9)     // Catch:{ Exception -> 0x0157 }
            r2.append(r3)     // Catch:{ Exception -> 0x0157 }
            java.lang.String r3 = new java.lang.String     // Catch:{ Exception -> 0x0157 }
            byte[] r4 = r15.buff     // Catch:{ Exception -> 0x0157 }
            int r5 = r15.rcvLen     // Catch:{ Exception -> 0x0157 }
            int r5 = r5 - r6
            java.lang.String r7 = "ASCII"
            r3.<init>(r4, r5, r6, r7)     // Catch:{ Exception -> 0x0157 }
            r2.append(r3)     // Catch:{ Exception -> 0x0157 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0157 }
            goto L_0x013c
        L_0x012e:
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x0157 }
            byte[] r3 = r15.buff     // Catch:{ Exception -> 0x0157 }
            int r4 = r15.rcvLen     // Catch:{ Exception -> 0x0157 }
            r5 = 14
            int r4 = r4 - r5
            java.lang.String r6 = "ASCII"
            r2.<init>(r3, r4, r5, r6)     // Catch:{ Exception -> 0x0157 }
        L_0x013c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0157 }
            r3.<init>()     // Catch:{ Exception -> 0x0157 }
            r3.append(r1)     // Catch:{ Exception -> 0x0157 }
            r3.append(r8)     // Catch:{ Exception -> 0x0157 }
            r3.append(r2)     // Catch:{ Exception -> 0x0157 }
            java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x0157 }
            r15.onServiceData(r1)     // Catch:{ Exception -> 0x0157 }
            goto L_0x0000
        L_0x0153:
            r15.connState = r0     // Catch:{ Exception -> 0x0157 }
            goto L_0x0000
        L_0x0157:
            r15.connState = r0     // Catch:{ Exception -> 0x0160 }
            r0 = 3000(0xbb8, double:1.482E-320)
            java.lang.Thread.sleep(r0)     // Catch:{ Exception -> 0x0160 }
            goto L_0x0000
        L_0x0160:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0000
        L_0x0166:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dev.kytech.ky224.tcpnet.TcpClient.run():void");
    }
}
