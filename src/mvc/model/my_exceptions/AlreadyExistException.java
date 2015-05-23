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
public class AlreadyExistException extends Exception {

    /**
     * Creates a new instance of <code>AlreadyExcistException</code> without
     * detail message.
     */
    public AlreadyExistException() {
    }

    /**
     * Constructs an instance of <code>AlreadyExcistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AlreadyExistException(String msg) {
        msg = "This connection is already excist";
    }
}

