package exceptions;

public class CTTServiceCallException extends Exception {
    public CTTServiceCallException(Exception e) {
        super(e);
    }
    
    public CTTServiceCallException(String message) {
        super(message);
    }
}
