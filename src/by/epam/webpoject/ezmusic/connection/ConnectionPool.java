package by.epam.webpoject.ezmusic.connection;

import by.epam.webpoject.ezmusic.exception.connectionpool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Антон on 16.07.2016.
 */
public class ConnectionPool {
    private static final int DEFAULT_POOL_SIZE = 5;

    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static ReentrantLock lock = new ReentrantLock();

    private BlockingQueue<ProxyConnection> connectionQueue;
    private int poolSize;


    private ConnectionPool(){
        initialize();
    }

    private void initialize() {
        try {
            DBManager dbResourceManager = DBManager.getInstance();

            String driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
            String url = dbResourceManager.getValue(DBParameter.DB_URL);
            String username = dbResourceManager.getValue(DBParameter.DB_USER);
            String password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
            poolSize = DEFAULT_POOL_SIZE;

            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            while (!isFull()) {
                Connection connection = DriverManager.getConnection(url, username, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                connectionQueue.add(proxyConnection);
            }
        }catch (NumberFormatException e){
            poolSize = DEFAULT_POOL_SIZE;
        } catch (ClassNotFoundException | SQLException | MissingResourceException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean isFull(){
        return connectionQueue.size() >= poolSize;
    }

    public static ConnectionPool getInstance(){
        if(!instanceCreated.get()) {//null
            lock.lock();
            try {
                if (!instanceCreated.get() && instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
          //log
        }
        return connection;
    }

    public void closeConnection(Connection connection){
        if(connection instanceof ProxyConnection){
            connectionQueue.offer((ProxyConnection) connection);
        }
    }

    public void close() throws ConnectionPoolException {
        for (ProxyConnection connection: connectionQueue) {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }

        }
    }
}
