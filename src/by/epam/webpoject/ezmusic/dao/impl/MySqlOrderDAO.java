package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Антон on 21.08.2016.
 */
public class MySqlOrderDAO extends OrderDAO{
    private static final String CREATE_ORDER_QUERY = "INSERT INTO ezmusicdb.order ();
    private static final MySqlOrderDAO instance = new MySqlOrderDAO();


    private MySqlOrderDAO(){}

    public static MySqlOrderDAO getInstance(){
        return instance;
    }

    @Override
    public Long create(Order instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(CREATE_ORDER_QUERY);
            statement.
        }catch (SQLException e){
            throw new DAOException("", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public Order find(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{

        }catch (SQLException e){
            throw new DAOException("", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{

        }catch (SQLException e){
            throw new DAOException("", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void update(Order instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{

        }catch (SQLException e){
            throw new DAOException("", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }
}