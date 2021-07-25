package springbook.leanringtest.spring.factorybean;

public class Message {

    String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    // 스태틱 팩토리 메소드
    public static Message newMessage(String text) {
        return new Message(text);
    }
}
