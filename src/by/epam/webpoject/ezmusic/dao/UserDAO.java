package by.epam.webpoject.ezmusic.dao;

import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Антон on 16.07.2016.
 */
public abstract class UserDAO implements AbstractDAO<User, Long> {
    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    public abstract User login(String userLogin, String userPassword) throws DAOException;
    public abstract boolean isLoginExist(String login) throws DAOException;

    @Override
    public void closeStatement(Statement statement) {
        try {
            if(statement != null)
                statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
