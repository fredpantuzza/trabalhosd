/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.validator;

/**
 *
 * @author frederico
 */
public class InvalidDTOException extends Exception {

    /**
     * Creates a new instance of
     * <code>InvalidDTOException</code> without detail message.
     */
    public InvalidDTOException() {
    }

    /**
     * Constructs an instance of
     * <code>InvalidDTOException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidDTOException(String msg) {
        super(msg);
    }
}
