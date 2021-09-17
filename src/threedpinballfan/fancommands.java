package threedpinballfan;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "fancommands", mixinStandardHelpOptions = true, version = "fancommands 1.0",
         description = "Prints the checksum (MD5 by default) of a file to STDOUT.")
class fancommands implements Runnable {


    @Parameters(paramLabel = "<table>", description = "DarkPrincess, ...")
    private String table = "";


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}