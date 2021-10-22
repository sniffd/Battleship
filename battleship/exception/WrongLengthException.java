package battleship.exception;

public class WrongLengthException extends RuntimeException {
    public WrongLengthException() {
        super();
    }

    public WrongLengthException(String message) {
        super(message);
    }

    public WrongLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongLengthException(Throwable cause) {
        super(cause);
    }

    protected WrongLengthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
