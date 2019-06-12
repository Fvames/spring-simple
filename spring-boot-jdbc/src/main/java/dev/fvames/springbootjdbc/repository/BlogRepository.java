package dev.fvames.springbootjdbc.repository;

import dev.fvames.springbootjdbc.domain.Blog;
import dev.fvames.springbootjdbc.domain.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;

/**
 * @version 2019/6/12 15:11
 */
@Repository
@Transactional
public class BlogRepository {

    private final JdbcTemplate jdbcTemplate;

    public BlogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean transactionalSave(Blog blog) {
        boolean operaterReslt = false;

        UserInfo user = new UserInfo();
        user.setUserName(blog.getTitle());
        Long userId = transactionalSaveUser(user);

        blog.setAuthorId(userId);

        operaterReslt = jdbcTemplate.execute("INSERT INTO blog(title, author_id) values (?, ?)",
                (PreparedStatementCallback<Boolean>) ps1 -> {
                    ps1.setString(1, blog.getTitle());
                    ps1.setLong(2, blog.getAuthorId());

                    return ps1.executeUpdate() > 0;
                });


        return operaterReslt;

    }

    public Long transactionalSaveUser(UserInfo userInfo) {

        final String sql = "INSERT INTO user_info(username) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"username"});
            preparedStatement.setString(1, userInfo.getUserName());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();

        /*NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        final String sql = "INSERT INTO user_info(username) VALUES (:username)";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("username", userInfo.getUserName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"username"});
        return keyHolder.getKey().longValue();*/
    }

}
