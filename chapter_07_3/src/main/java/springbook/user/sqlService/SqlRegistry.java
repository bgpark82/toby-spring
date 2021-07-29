package springbook.user.sqlService;

public interface SqlRegistry {

    void registerSql(String key, String sql); // sql을 키와 함께 등록

    String findSql(String key) throws SqlNotFoundException; // key로 SQL을 검색, 실패시 예외 던진다
}
