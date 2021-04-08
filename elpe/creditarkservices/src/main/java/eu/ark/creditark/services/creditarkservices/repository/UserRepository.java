package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.utils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserRepository {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean insertIfNotExists(String loginName) throws DatabaseConnectionException {
        logger.info("{}", loginName);
        PreparedStatement st = null;
        PreparedStatement insert = null;
        PreparedStatement update = null;
        ResultSet rows = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            st = conn.prepareStatement("SELECT login_name FROM core.app_user where login_name=?");
            st.setString(1, loginName);
            st.execute();
            rows = st.getResultSet();

            if(rows.next()) return true;
            else {
                logger.info("Configure user {}", loginName);
                insert = conn.prepareStatement("insert into core.app_user values (?, ?, 1, '{31}', 9999, 200, 2)");
                insert.setString(1, loginName);
                insert.setString(2, loginName);
                insert.execute();

                update = conn.prepareStatement("select core.change_password(?, ?)");
                update.setString(1, loginName);
                update.setString(2, loginName);
                update.execute();
            }

        }  catch (Exception e) {
            e.printStackTrace();
            logger.error("DB userExists for " + loginName);
            throw new DatabaseConnectionException("Error during loading user");
        } finally {
            DbUtils.close(rows, st);
            DbUtils.close(insert);
            DbUtils.close(update);
        }

        return false;
    }

    public void attachUser() {


    }

}
