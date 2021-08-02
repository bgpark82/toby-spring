package springbook.leanringtest.jdk;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UppercaseAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String ret = (String) methodInvocation.proceed(); // 리플렉션의 Method와 달리 메소드 실행 시, 타겟 객체를 전달할 필요가 없다. MethodInvocation은 메소드 정보와 함께 타겟 객체를 알고 있기 때문이다
        return ret.toUpperCase(); // 부가기능 적용
    }
}
