package threedpinballfan;


public class UpLoadData {
    private static final String DEFAULT_HAS_2_DATA_LENTH = "abe";
    private static final String DEFAULT_NO_DATA_LENTH = "abc";
    private static final String TAG = "UpLoadData";
    private static final String clearDiskGarbage = "89";
    private static final String commandType = "c";
    private static final String commandType_data = "d";
    private static final String contentDefaultValue = "00";
    private static final String deleteFile = "39";
    private static final String direction = "1";
    private static final String end = "a4a8c2e3";
    private static final String file_data_stream_transfer_start = "88";
    private static final String file_transfer_start = "66";
    private static final String formatDisk = "88";
    private static final String getFileList = "38";
    private static final String getWifiPwd = "42";
    private static final String head = "c";
    private static final String head_data = "d";
    private static final String lightDown = "43";
    private static final String lightUp = "44";
    private static final String modifyFileName = "43";
    private static final String modifyWifiName = "41";
    private static final String playFile = "40";
    private static final String playModelLoop = "37";
    private static final String playModelSingle = "36";
    private static final String playNext = "32";
    private static final String playPause = "34";
    private static final String playStart = "35";
    private static final String playlast = "33";
    static int powerFlag = 0;
    private static final String powerOff = "94";
    private static final String powerOn = "95";
    private static final String restart = "31";
    private static final String sendAPInfo = "99";
    private static final String shutDown = "30";
    private static final String startRealTimePlay = "98";
    private static final String vesion = "3";

    private static String noDataContentPackage(String str) {
        return "c31c" + str + DEFAULT_NO_DATA_LENTH + end;
    }

    private static String fixedDataContentPackage(String str, String str2) {
        return "c31c" + str + DEFAULT_HAS_2_DATA_LENTH + str2 + end;
    }

    private static String dataFrame(String str, String str2) {
        return "d31d" + str + DEFAULT_HAS_2_DATA_LENTH + str2 + end;
    }
//
//    private static String dynamicDataContentPackage(String str, String str2) {
//        return "c31c" + str + getDynamicCommandFrameLenth(str2.length()) + str2 + end;
//    }

    
    /*
    private static String getDynamicCommandFrameLenth(int i) {
        return new String(new char[]{(char) ((i / avutil.AV_PIX_FMT_BAYER_GBRG8) + 97), (char) (((i / 17) % 19) + 98), (char) (((i % avutil.AV_PIX_FMT_BAYER_GBRG8) % 17) + 99)});
    }
*/
    private static String getDynamicDataFrameLenth(int i) {
        return new String(new char[]{(char) ((i / 221) + 68), (char) (((i / 13) % 17) + 69), (char) (((i % 221) % 13) + 70)});
    }

    private static String intTo2Str(int i) {
        if (i >= 0 && i < 10) {
            return "0" + i;
        } else if (i < 10 || i >= 100) {
            return i >= 100 ? sendAPInfo : contentDefaultValue;
        } else {
            return String.valueOf(i);
        }
    }

    public static String lightUp() {
        return noDataContentPackage(lightUp);
    }

    public static String lightDown() {
        return noDataContentPackage("43");
    }

    public static String powerOn() {
        if (powerFlag == 0) {
            powerFlag = 1;
            return noDataContentPackage(powerOff);
        }
        powerFlag = 0;
        return noDataContentPackage(powerOn);
    }

    public static String playNext() {
        return noDataContentPackage(playNext);
    }

    public static String playLast() {
        return noDataContentPackage(playlast);
    }

    public static String playPause() {
        return noDataContentPackage(playPause);
    }

    public static String playStart() {
        return noDataContentPackage(playStart);
    }

    public static String playModelSingle() {
        return noDataContentPackage(playModelSingle);
    }

    public static String playModelLoop() {
        return noDataContentPackage(playModelLoop);
    }

    public static String getFileList() {
        return noDataContentPackage(getFileList);
    }

    public static String deleteFile(int i) {
        return fixedDataContentPackage(deleteFile, intTo2Str(i));
    }

    public static String playFile(int i) {
        return fixedDataContentPackage(playFile, intTo2Str(i));
    }

//    public static String modifyWifiNameAndPwd(String str, String str2) {
//        return dynamicDataContentPackage(modifyWifiName, intTo2Str(str.length()) + str + intTo2Str(str2.length()) + str2);
//    }
//
//    public static String sendAPInfoM(String str, String str2) {
//        return dynamicDataContentPackage(sendAPInfo, intTo2Str(str.length()) + str + intTo2Str(str2.length()) + str2);
//    }
//
//    public static String modifyFileName(int i, String str) {
//        return dynamicDataContentPackage("43", i + str);
//    }

    public static String getWifiPwd() {
        return noDataContentPackage(getWifiPwd);
    }

    public static String formatDisk() {
        return noDataContentPackage("88");
    }

    public static String clearDiskGarbage() {
        return noDataContentPackage(clearDiskGarbage);
    }

    public static String startRealTimePlay() {
        return noDataContentPackage(startRealTimePlay);
    }
//
//    public static String fileTransferStart(String str) {
//        return dynamicDataContentPackage(file_transfer_start, str);
//    }
//
//    public static String fileDataStreamTransferStart(String str) {
//        return dynamicDataContentPackage("88", str);
//    }
}
