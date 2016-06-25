package backend.utils.Exceptions;

/**
 * Created by Vojta Podhajsky on 25.06.2016.
 */
public class XmlValidationException extends RuntimeException {
    public XmlValidationException() {
    }

    public XmlValidationException(String message) {
        super(message);
    }

    public XmlValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlValidationException(Throwable cause) {
        super(cause);
    }

    public XmlValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
