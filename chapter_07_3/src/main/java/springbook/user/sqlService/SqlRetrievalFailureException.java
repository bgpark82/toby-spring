package springbook.user.sqlService;

public class SqlRetrievalFailureException extends RuntimeException{

    public SqlRetrievalFailureException(String message) {
        super(message);
    }

    // cause : SQL을 가져오는데 실패한 근본 원인을 담을 수 있도록 중첩예외를 저장하는 생성자를 만들어둔다
    public SqlRetrievalFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
