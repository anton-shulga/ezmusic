package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Антон on 21.08.2016.
 */
public class MySqlOrderDAO extends OrderDAO{
    private static final String CREATE_ORDER_QUERY = "INSERT INTO ezmusicdb.order (user_id, order_is_paid) VALUES (? ,?, ?)";
    private static final MySqlOrderDAO instance = new MySqlOrderDAO();
    private static final String FIND_ORDER_QUERY = "SELECT order_id, user_id, order_is_paid, order_total_cost FROM ezmusicdb.order WHERE order_id = ?";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM ezmusicdb.order WHERE order_id = ?";
    private static final String  UPDATE_ORDER_QUERY = "UPDATE ezmusicdb.order SET user_id = ?, order_is_paid = ?, order_total_cost = ? WHERE order_id = ?";
    private static final String FIND_ORDER_BY_USER_ID_QUERY = "SELECT order_id, user_id, order_is_paid, order_total_cost FROM ezmusicdb.order WHERE user_id = ?";


    private MySqlOrderDAO(){}

    public static MySqlOrderDAO getInstance(){
        return instance;
    }

    @Override
    public Long create(Order instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Long generatedId = null;
        try{
            statement = connection.prepareStatement(CREATE_ORDER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, instance.getUserId());
            statement.setBoolean(2, instance.isPaid());
            statement.setDouble(3, instance.getTotalCost());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        }catch (SQLException e){
            throw new DAOException("Create order dao exception", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return generatedId;
    }

    @Override
    public Order find(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Order order = null;
        try{
            statement = connection.prepareStatement(FIND_ORDER_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                order = new Order();
                order.setOrderId(resultSet.getLong(1));
                order.setUserId(resultSet.getLong(2));
                order.setPaid(resultSet.getBoolean(3));
                order.setTotalCost(resultSet.getDouble(4));
            }
        }catch (SQLException e){
            throw new DAOException("Find order dao exception", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return order;
    }

    @Override
    public void delete(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(DELETE_ORDER_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DAOException("Delete order dao exception", e);
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
            statement = connection.prepareStatement(UPDATE_ORDER_QUERY);
            statement.setLong(1, instance.getUserId());
            statement.setBoolean(2, instance.isPaid());
            statement.setDouble(3, instance.getTotalCost());
            statement.setLong(4, instance.getOrderId());
        }catch (SQLException e){
            throw new DAOException("Update order dao exception", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public ArrayList<Order> findByUserId(Long userId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Order> orderList = new ArrayList<>();
        try{
            statement = connection.prepareStatement(FIND_ORDER_BY_USER_ID_QUERY);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Order order = null;
            while (resultSet.next()){
                order = new Order();
                order.setOrderId(resultSet.getLong(1));
                order.setUserId(resultSet.getLong(2));
                order.setPaid(resultSet.getBoolean(3));
                order.setTotalCost(resultSet.getDouble(4));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Find order dao exception", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return orderList;
    }

    @Override
    public Order findCartByUserId(Long userId) {
        return null;
    }
}