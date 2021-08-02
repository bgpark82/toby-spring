package springbook.user.sqlService;

public class DefaultSqlService extends BaseSqlService{

    public DefaultSqlService() {
        setSqlReader(new JaxbXmlSqlReader()); // 생성자에서 디폴트 의존객체를 직접 만들어서 스스로 DI
        setSqlRegistry(new HashMapSqlRegistry());
    }
}
