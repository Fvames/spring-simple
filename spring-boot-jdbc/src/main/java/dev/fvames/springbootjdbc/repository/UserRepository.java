package dev.fvames.springbootjdbc.repository;

import dev.fvames.springbootjdbc.domain.UserInfo;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @version 2019/6/11 15:43
 */
@Repository
public class UserRepository {

    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public boolean jdbcSave(UserInfo userInfo) {
        boolean operateResult = false;

        //加、连、预、执、释
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user_info(user_name) VALUES (?)");

            preparedStatement.setString(1, userInfo.getUserName());

            operateResult = preparedStatement.executeUpdate() > 0;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return operateResult;
    }
}
