package aegeus.com.aegeus.util.exceptions;

public class NoneNearbyException extends RuntimeException {
	public NoneNearbyException() { super(); }
	public NoneNearbyException(String message) { super(message); }
	public NoneNearbyException(String message, Throwable cause) { super(message, cause); }
	public NoneNearbyException(Throwable cause) { super(cause); }
}
