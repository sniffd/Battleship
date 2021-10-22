package battleship.exception;

public class WrongLocationException extends RuntimeException {
    public WrongLocationException() {
        super();
    }

    public WrongLocationException(String message) {
        super(message);
    }

    public WrongLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongLocationException(Throwable cause) {
        super(cause);
    }

    protected WrongLocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
