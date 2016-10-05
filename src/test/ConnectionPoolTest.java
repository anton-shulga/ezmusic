package test;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Антон on 26.09.2016.
 */
public class ConnectionPoolTest {
    @Test
    public void getConnectionTest(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ProxyConnection connection = connectionPool.getConnection();
        assertNotNull(connection);
    }
    @Test
    public void getInstanceTest(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        assertNotNull(connectionPool);
    }
}
