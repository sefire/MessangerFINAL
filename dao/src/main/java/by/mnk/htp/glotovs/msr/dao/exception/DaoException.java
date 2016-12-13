package by.mnk.htp.glotovs.msr.dao.exception;

/**
 * Created by Sefire on 25.10.2016.
 */
/**/
public class DaoException extends Exception{
    public DaoException(String message, Exception exception) {
        super(message, exception);
    }

    public DaoException(Exception exception) {
        super(exception);
    }
}
