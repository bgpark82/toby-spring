package springbook.user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

public class MockMailSender implements MailSender {

    private List<String> requests = new ArrayList<>();

    public List<String> getRequests() {
        return requests;
    }

    @Override
    public void send(SimpleMailMessage mailMessage) throws MailException {
        requests.add(mailMessage.getTo()[0]); // 첫번째 수신자 메일 주소만 저장
    }

    @Override
    public void send(SimpleMailMessage[] simpleMailMessages) throws MailException {

    }
}
