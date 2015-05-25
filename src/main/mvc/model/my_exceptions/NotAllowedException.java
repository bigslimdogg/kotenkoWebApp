/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.my_exceptions;

/**
 *
 * @author Nick
 */
public class NotAllowedException extends Exception {

    /**
     * Creates a new instance of <code>NotAllowedException</code> without detail
     * message.
     */
    public NotAllowedException() {
    }

    /**
     * Constructs an instance of <code>NotAllowedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotAllowedException(String msg) {
        super(msg);
    }
}
