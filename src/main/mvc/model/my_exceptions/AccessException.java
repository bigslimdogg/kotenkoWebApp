
package mvc.model.my_exceptions;

public class AccessException extends Exception {

  
    public AccessException() {
    }

    public AccessException(String msg) {
        msg = "Wrong access";
    }
}
