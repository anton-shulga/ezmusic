package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.UserDAO;
import by.epam.webpoject.ezmusic.entity.User;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Антон on 16.07.2016.
 */
public class MySqlUserDAO implements UserDAO {
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT user_id, user_name, user_surname, user_login,   user_password, user_email, user_phone, user_photo_path, user_balance, user_is_admin, user_is_banned FROM ezmusicdb.user WHERE user_login = ? AND user_password = ?";
    private static final String CREATE_USER_QUERY = "INSERT INTO ezmusicdb.user (user_name, user_surname, user_login, user_password, user_email, user_phone, user_photo_path, user_balance, user_is_admin, user_is_banned) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT user_id, user_name, user_surname, user_login, user_password,user_email, user_phone, user_photo_path, user_balance, user_is_admin, user_is_banned FROM ezmusicdb.user WHERE user_id = ? ";
    private static final String DELETE_USER_BY_ID = "DELETE  FROM ezmusicdb.user WHERE user_id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE ezmusicdb.user SET user_name = ?, user_surname = ?, user_login = ?, user_password = ?, user_email = ?, user_phone = ?, user_photo_path = ?, user_balance = ?, user_is_admin = ?, user_is_banned = ? WHERE user_id = ?";
    private static final String FIND_USER_BY_LOGIN_QUERY = "SELECT user_id FROM ezmusicdb.user WHERE user_login = ?";

    private static final MySqlUserDAO instance = new MySqlUserDAO();

    private MySqlUserDAO() {
    }

    public static MySqlUserDAO getInstance() {
        return instance;
    }

    @Override
    public Long create(User instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Long generatedId = null;
        try {
            if (!isLoginExist(instance.getLogin())) {
                statement = connection.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, instance.getName());
                statement.setString(2, instance.getSurname());
                statement.setString(3, instance.getLogin());
                statement.setString(4, instance.getPassword());
                statement.setString(5, instance.getEmail());
                statement.setString(6, instance.getPhone());
                statement.setString(7, instance.getPhotoPath());
                statement.setDouble(8, instance.getBalance());
                statement.setBoolean(9, instance.getIsAdmin());
                statement.setBoolean(10, instance.isBanned());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Creating user error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return generatedId;
    }

    @Override
    public User find(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setPhone(resultSet.getString(7));
                user.setPhotoPath(resultSet.getString(8));
                user.setBalance(resultSet.getDouble(9));
                user.setAdmin(resultSet.getBoolean(10));
                user.setBanned(resultSet.getBoolean(11));
            }
        } catch (SQLException e) {
            throw new DAOException("Finding user error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return user;
    }

    @Override
    public void delete(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_USER_BY_ID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Deleting user error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void update(User instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, instance.getName());
            statement.setString(2, instance.getSurname());
            statement.setString(3, instance.getLogin());
            statement.setString(4, instance.getPassword());
            statement.setString(5, instance.getEmail());
            statement.setString(6, instance.getPhone());
            statement.setString(7, instance.getPhotoPath());
            statement.setDouble(8, instance.getBalance());
            statement.setBoolean(9, instance.getIsAdmin());
            statement.setBoolean(10, instance.isBanned());
            statement.setLong(11, instance.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Updating user error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public User login(String userLogin, String userPassword) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY);
            statement.setString(1, userLogin);
            statement.setString(2, userPassword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setPhone(resultSet.getString(7));
                user.setPhotoPath(resultSet.getString(8));
                user.setBalance(resultSet.getDouble(9));
                user.setAdmin(resultSet.getBoolean(10));
                user.setBanned(resultSet.getBoolean(11));
            }
        } catch (SQLException e) {
            throw new DAOException("Login user error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return user;
    }

    public boolean isLoginExist(String login) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_LOGIN_QUERY);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return false;

    }
}
