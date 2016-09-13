package by.epam.webpoject.ezmusic.listener;

import by.epam.webpoject.ezmusic.constant.ContextParameter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Антон on 13.09.2016.
 */
public class FileLocationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        String albumImageDirectory = ctx.getInitParameter(ContextParameter.ALBUM_IMAGE_DIRECTORY);
        ctx.setAttribute(ContextParameter.ALBUM_IMAGE_DIRECTORY, albumImageDirectory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
