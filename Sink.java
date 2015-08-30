import java.util.Map;

public abstract class Sink<A, B> implements Pipable {
	public Map<A, B> input;
	
    public Sink() {
    	System.err.println("Sink instantiated.");
    }
}