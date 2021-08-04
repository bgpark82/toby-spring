package springbook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springbook.user.dao.UserDao;
import springbook.user.service.DummyMailSender;
import springbook.user.service.UserService;
import springbook.user.service.UserServiceTest;
import springbook.user.sqlService.EnableSqlService;
import springbook.user.sqlService.SqlMapConfig;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@EnableSqlService
@EnableTransactionManagement
@ComponentScan(basePackages = "springbook.user")
@PropertySource("/database.properties")
public class AppContext implements SqlMapConfig{

    @Value("${db.driverClass}") Class<? extends Driver> driverClass;
    @Value("${db.url}") String url;
    @Value("${db.username}") String username;
    @Value("${db.password}") String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Override
    public Resource getSqlMapResource() {
        return new ClassPathResource("sqlmap.xml", UserDao.class);
    }

    @Configuration
    @Profile("production")
    public static class ProductionAppContext {

        @Bean
        public MailSender mailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("localhost");
            return mailSender;
        }
    }

    @Configuration
    @Profile("test")
    public static class TestAppContext {

        @Bean
        public UserService testUserService() {
            return new UserServiceTest.TestUserServiceImpl();
        }

        @Bean
        public MailSender mailSender() {
            return new DummyMailSender();
        }
    }
}
