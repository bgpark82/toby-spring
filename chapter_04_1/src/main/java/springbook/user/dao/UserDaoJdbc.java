package springbook.user.dao;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import springbook.user.domain.User;
import springbook.user.exception.DuplicateUserIdException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao{

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(final User user) throws DuplicateKeyException {
        this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
                    user.getId(), user.getName(), user.getPassword());

    }

    public void deleteAll() {
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                return connection.prepareStatement("delete from users");
            }
        });
    }

    public User get(String id)  {
        return this.jdbcTemplate.queryForObject("select * from users where id = ?",
                new Object[] {id},
                this.userMapper);
    }

    public int getCount()  {
        return this.jdbcTemplate.queryForInt("select count(*) from users");
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
    }
}
