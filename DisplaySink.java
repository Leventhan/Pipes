import java.util.Map;


public class DisplaySink extends Sink<Object, Object> {

    public DisplaySink() {
		super();
	}

	public void receive(Map<String, String> data){
		System.err.println("Sink receiving data.");
    }
}
