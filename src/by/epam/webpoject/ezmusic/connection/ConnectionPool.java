package by.epam.webpoject.ezmusic.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_POOL_SIZE = 5;

    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static ReentrantLock lock = new ReentrantLock();

    private BlockingQueue<ProxyConnection> connectionQueue;
    private int poolSize;


    private ConnectionPool() {
        initialize();
    }

    private void initialize() {

        try {
            DBManager dbResourceManager = DBManager.getInstance();

            dbResourceManager.initialize();

            String driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
            String url = dbResourceManager.getValue(DBParameter.DB_URL);
            String username = dbResourceManager.getValue(DBParameter.DB_USER);
            String password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

            try {
                poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
                if (poolSize < DEFAULT_POOL_SIZE) {
                    poolSize = DEFAULT_POOL_SIZE;
                }
            } catch (NumberFormatException e) {
                poolSize = DEFAULT_POOL_SIZE;
            }

            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            int createdConnectionNumber = 0;
            for (int i = 0; i < poolSize; i++) {
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    ProxyConnection proxyConnection = new ProxyConnection(connection);
                    connectionQueue.add(proxyConnection);
                    createdConnectionNumber++;
                } catch (SQLException e) {
                    LOGGER.error("Create database connection error", e);
                }
            }

            if (createdConnectionNumber < DEFAULT_POOL_SIZE) {
                LOGGER.fatal("Database connection error");
                throw new RuntimeException("Database connection error");
            }
        } catch (ClassNotFoundException | MissingResourceException e) {
            LOGGER.fatal("Database connection error");
            throw new RuntimeException("Database connection error", e);
        }

    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.getAndSet(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        return connection;
    }

    public void returnConnection(ProxyConnection connection) {
        connectionQueue.offer(connection);
    }

    public void closePool() {
        for (ProxyConnection connection : connectionQueue) {
            connection.closeConnection();
        }
    }
}
