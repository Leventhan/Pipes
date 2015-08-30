import java.util.HashMap;
import java.util.Map;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String titles = "The Day after Tomorrow, Fast and Furious, Man of Steel";
		String ignored = "are, is, the, of, and, as, a, after";
		Map<String, String> data = new HashMap<String, String>();
		data.put("titles", titles);
		data.put("ignored", ignored);
	
		Sink<Object, Object> sink = new DisplaySink();
		Pipe sinkPipe = new Pipe(sink);
		Filter circularShiftFilter = new circularShiftFilter(sinkPipe);
		Pipe circularShiftPipe = new Pipe(circularShiftFilter);
		Pump pump = new KWICPump(data, circularShiftPipe);
		
		pump.start();
	}
}
