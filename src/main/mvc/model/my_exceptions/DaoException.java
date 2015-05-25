package mvc.model.my_exceptions;

/**
 * Created by Nick on 15.05.2015.
 */
public class DaoException  extends Exception{
    /**
     * Creates a new instance of <code>NotAllowedException</code> without detail
     * message.
     */
    public DaoException() {
    }

    /**
     * Constructs an instance of <code>NotAllowedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DaoException(String msg) {
        super(msg);
    }
}
