package springbook.user.sqlService;

public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest{

    @Override
    protected UpdatableSqlRegistry createUpdatableRegistry() {
        return new ConcurrentHashMapSqlRegistry();
    }
}
