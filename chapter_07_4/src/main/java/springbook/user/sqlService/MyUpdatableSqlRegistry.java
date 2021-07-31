package springbook.user.sqlService;

import java.util.HashMap;
import java.util.Map;

public class MyUpdatableSqlRegistry implements UpdatableSqlRegistry{

    private Map<String, String> sqlMap = new HashMap<String, String>();

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @Override
    public String findSql(String key) throws SqlNotFoundException {
        String sql = sqlMap.get(key);
        if (sql == null) {
            throw new SqlNotFoundException(key+"에 대한 SQL을 찾을 수 없습니다");
        }
        return sql;
    }

    @Override
    public void updateSql(String key, String sql) throws SqlUpdateFailureException {

    }

    @Override
    public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException {

    }
}
