package springbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import springbook.user.dao.UserDao;
import springbook.user.service.DummyMailSender;
import springbook.user.service.UserService;

import static springbook.user.service.UserServiceTest.TestUserServiceImpl;

@Configuration
@ComponentScan(basePackages = "springbook.user")
public class TestAppContext {

    @Autowired
    private UserDao userDao;

    @Bean
    public UserService testUserService() {
        TestUserServiceImpl testService = new TestUserServiceImpl();
        testService.setUserDao(userDao);
        testService.setMailSender(mailSender());
        return testService;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }
}
