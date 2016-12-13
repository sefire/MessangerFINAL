package by.mnk.htp.glotovs.msr.services.exception;

/**
 * Created by Sefire on 25.10.2016.
 */
public class ServiceException extends Exception {
    public ServiceException(String message, Exception exception) {
        super(message, exception);
    }

    public ServiceException(Exception exception) {
        super(exception);
    }
}
