package springbook.leanringtest.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {

    // 어떤 종류이 인터페이스를 구현한 타겟에도 적용가능하도록 Object 타입으로 수정
    Object target;

    public UppercaseHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = method.invoke(target, args); // 메소드 호출을 타겟으로 위임
        if (ret instanceof String) {
            return ((String) ret).toUpperCase();
        } else {
            return ret; // 부가기능 제공
        }
    }
}
