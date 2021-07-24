package springbook.user.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor {

    private PlatformTransactionManager transactionManager; // 트랜잭션 기능을 제공하는데 필요한 트랜잭션 매니저

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Object ret = invocation.proceed(); // 콜백을 호출해서 타겟 메소드를 실행한다.
            transactionManager.commit(status);
            return ret;
        } catch (RuntimeException e) { // JDK 다이나믹 프록시가 제공하는 Method와 달리 스프링의 MethodInvocation을 통한 타겟호출은 예외가 포장되지 않고 타겟에서 보낸 그대로 전달된다. 그냥 그대로 잡아서 처리하면 된다
            transactionManager.rollback(status);
            throw e;
        }
    }
}
