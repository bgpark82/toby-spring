package springbook.leanringtest.spring.embeddeddb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

public class EmbeddedDbTest {

    EmbeddedDatabase db;

    SimpleJdbcTemplate template;

    @Before
    public void setUp() throws Exception {
        db = new EmbeddedDatabaseBuilder()
                .setType(HSQL)
                .addScript("classpath:/schema.sql")
                .addScript("classpath:/data.sql")
                .build();
        template = new SimpleJdbcTemplate(db);
    }

    @After
    public void tearDown() throws Exception {
        db.shutdown();
    }

    @Test
    public void initData() {
        assertThat(template.queryForInt("select count(*) from sqlmap"), is(2));

        List<Map<String, Object>> list = template.queryForList("select * from sqlmap order by key_");
        assertThat((String) list.get(0).get("key_"), is("KEY1"));
        assertThat((String) list.get(0).get("sql_"), is("SQL1"));
        assertThat((String) list.get(1).get("key_"), is("KEY2"));
        assertThat((String) list.get(1).get("sql_"), is("SQL2"));
    }

    @Test
    public void insert() {
        template.update("insert into sqlmap(key_, sql_) values(?,?)", "KEY3", "SQL3");
        assertThat(template.queryForInt("select count(*) from sqlmap"), is(3));
    }
}
