package springbook.leanringtest.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class MessageFactoryBean implements FactoryBean<Message> {

    String text;

    // 오브젝트를 생성할 때 필요한 정보를 팩토리 빈의 프로퍼티로 설정해서 대신 DI 받도록 한다
    public void setText(String text) {
        this.text = text;
    }

    // 실제 빈으로 사용될 객체를 직접 생성한다
    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    // getObject가 반환하는 객체가 싱글톤인지 알려준다
    // 이 팩토리 빈은 요청할 때마다 새로운 객체를 만드므로 false로 지정
    // 이것은 팩토리 빈의 동작방식에 관한 설정이고 만들어진 빈 객체는 싱글톤으로 스프링이 관리할 수 있다
    @Override
    public boolean isSingleton() {
        return false;
    }
}
