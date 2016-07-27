import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Lets user choose configuration file and drives application
 *
 * @author Kyle Heneman
 * @since 11/12/2015
 */
public class ReadFile {
	//ArrayList to hold read-in settings
	static ArrayList<Integer> configArray = new ArrayList<Integer>();

	/**
	 * Main method that runs the program
     *
	 * @param args Not used
     *
	 * @throws IOException if error occurs
	 */
	public static void main(String[] args) throws IOException {
		// New file chooser modal
		JFileChooser chooser = new JFileChooser();

        chooser.showOpenDialog(null);

        // File object to hold selected configuration file
		File file = chooser.getSelectedFile();

        // FileInputStream to read contents from chosen configuration file
		FileInputStream fstream = new FileInputStream(file);

        // BufferedReader to read input from fstream
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        // Placeholder to hold configuration values
		String strLine;

        // While there are still more lines to read
		while ((strLine = br.readLine()) != null)   {
            // Parse line to int
			int intConfig = Integer.parseInt(strLine);

            // Add to configArray
			configArray.add(intConfig);
		}

        // Close the BufferedReader
		br.close();

        RefrigeratorDisplay display = new GUI();
	}

	/**
	 * Returns the config array
	 * @return the config array
	 */
	public static ArrayList<Integer> getConfig(){
		return configArray;
	}
}