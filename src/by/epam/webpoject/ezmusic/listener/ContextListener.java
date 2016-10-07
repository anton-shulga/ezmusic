package by.epam.webpoject.ezmusic.listener;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Антон on 19.08.2016.
 */
public class ContextListener implements ServletContextListener {
    private ConnectionPool connectionPool = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (connectionPool != null) {
            connectionPool.closePool();
        }
    }
}
