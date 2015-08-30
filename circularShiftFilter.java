import java.util.HashMap;
import java.util.Map;


public class circularShiftFilter extends Filter {

	public circularShiftFilter(Pipe output) {
		super(output);
	}

	@Override
	public Map<String, String> transform(Map<String, String> data) {
		Map<String, String> result = new HashMap<String, String>();
		System.err.println("Filter transforming data.");
		return result;
	}
}
