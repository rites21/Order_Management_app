package order_management_app.Exceptions;

import java.io.Serial;

public class RepositoryException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public RepositoryException(String message) {
        super(message);
    }
}