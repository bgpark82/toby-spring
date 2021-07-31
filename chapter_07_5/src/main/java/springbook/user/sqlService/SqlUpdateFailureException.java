package springbook.user.sqlService;

public class SqlUpdateFailureException extends RuntimeException{

    public SqlUpdateFailureException(String message) {
        super(message);
    }
}
