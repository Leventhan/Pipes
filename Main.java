import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		int whichArchitecture = 1;
		String titles[] = {};
		String ignored[] = {};
		
		// Parse CLI arguments
		if (args.length == 3) {
		    try {
		    	whichArchitecture = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		        System.exit(1);
		    }
		    
		    // Parse files
		    titles = readLines(args[1]);
		    ignored = readLines(args[2]);
		} else {
	        System.err.println("Needs 3 arguments.");
	        System.exit(1);
		}
		
		// TODO: Switch between architecture		
		if (whichArchitecture == 1){
			
		} else {
			
		}
		
		// Prepare data for Pipe and Filter
		Map<String, String[]> data = new HashMap<String, String[]>();
		data.put("titles", titles);
		data.put("ignored", ignored);
	
		// Pipe and Filter
		Sink<Object, Object> sink = new DisplaySink();
		Pipe sinkPipe = new Pipe(sink);
		Filter alphabeticalSortFilter = new alphabeticalSortFilter(sinkPipe);
		Pipe alphabeticalSortPipe = new Pipe(alphabeticalSortFilter);
		Filter pruneIgnoredFilter = new pruneIgnoredFilter(alphabeticalSortPipe);
		Pipe pruneIgnoredPipe = new Pipe(pruneIgnoredFilter);
		Filter circularShiftFilter = new circularShiftFilter(pruneIgnoredPipe);
		Pipe circularShiftPipe = new Pipe(circularShiftFilter);
		Pump pump = new KWICPump(data, circularShiftPipe);
		pump.start();
	}

	private static String[] readLines(String filepath) {
	    List<String> titles = new ArrayList<String>();
	    File file = new File(filepath);
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String text = null;

	        while ((text = reader.readLine()) != null) {
	        	titles.add(text);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (reader != null) {
	                reader.close();
	            }
	        } catch (IOException e) {
	        }
	    }
	    
		return titles.toArray(new String[titles.size()]);
	}
}
