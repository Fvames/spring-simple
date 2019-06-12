package dev.fvames.springbootjdbc.repository;

import dev.fvames.springbootjdbc.domain.UserInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @version 2019/6/11 15:43
 */
@Repository
public class UserRepository {

    private final DataSource dataSource;
    private final DataSource masterDataSource;
    private final DataSource salveDataSource;
    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager platformTransactionManager;

    public UserRepository(DataSource dataSource,
                          @Qualifier("masterDataSource") DataSource masterDataSource,
                          @Qualifier("salveDataSource") DataSource salveDataSource,
                          JdbcTemplate jdbcTemplate,
                          PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.masterDataSource = masterDataSource;
        this.salveDataSource = salveDataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }


    /**
     * 编程式
     *
     * @param userInfo
     */
    public boolean save(UserInfo userInfo) {
        Boolean operaterResult = false;
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        operaterResult = jdbcTemplate.execute("INSERT INTO user_info(username) VALUES (?)"
                , (PreparedStatementCallback<Boolean>) ps -> {
                    ps.setString(1, userInfo.getUserName());
                    return ps.executeUpdate() > 0;
                });
        platformTransactionManager.commit(transactionStatus);

        return operaterResult;
    }


    @Transactional
    public boolean transactionalSave(UserInfo userInfo) {

        boolean operaterResult = false;
        operaterResult = jdbcTemplate.execute("INSERT INTO user_info(username) VALUES (?)"
                , (PreparedStatementCallback<Boolean>) ps -> {
                    ps.setString(1, userInfo.getUserName());
                    return ps.executeUpdate() > 0;
                });

        return operaterResult;
    }


    public boolean jdbcSave(UserInfo userInfo) {
        boolean operateResult = false;

        //加、连、预、执、释
        Connection connection = null;
        try {
            connection = salveDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user_info(username) VALUES (?)");

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
