package springbook.user.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Proxy;

// 범용적으로 사용하기 위해 Object
public class TxProxyFactoryBean implements FactoryBean<Object> {

    Object target;
    PlatformTransactionManager transactionManager;
    String pattern;
    Class<?> serviceInterface;

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setServiceInterface(Class<?> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @Override
    public Object getObject() throws Exception {
        TransactionHandler txHandler = new TransactionHandler();
        txHandler.setTarget(target);
        txHandler.setTarget(transactionManager);
        txHandler.setPattern(pattern);

        return Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] { serviceInterface },
                txHandler);
    }

    // 팩토리 빈이 생성하는 객체 타입은 DI받은 인터페이스타입에 따라 달라진
    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    // 싱글톤 빈이 아니라는 뜻이 아니라 getObject가 매번 같은 오브젝트를 리턴하지 않는다는 의미이다
    @Override
    public boolean isSingleton() {
        return false;
    }
}
