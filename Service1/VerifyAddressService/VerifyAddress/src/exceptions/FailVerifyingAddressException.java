package exceptions;

/**
 * Use when fails verifying an address.
 */
public class FailVerifyingAddressException extends Exception {
    public FailVerifyingAddressException(Exception e) {
        super(e);
    }
}
