package springbook.user.sqlService;

import java.util.Map;

public class ConcurrentHashMapSqlRegistry implements UpdatableSqlRegistry{

    @Override
    public void registerSql(String key, String sql) {

    }

    @Override
    public String findSql(String key) throws SqlNotFoundException {
        return null;
    }

    @Override
    public void updateSql(String key, String sql) throws SqlUpdateFailureException {

    }

    @Override
    public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException {

    }
}
