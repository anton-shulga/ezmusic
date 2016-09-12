package by.epam.webpoject.ezmusic.listener;

import by.epam.webpoject.ezmusic.constant.RequestParameter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * Created by Антон on 13.09.2016.
 */
public class FileLocationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String rootPath = System.getProperty("catalina.home");
        ServletContext ctx = servletContextEvent.getServletContext();
        String relativePath = ctx.getInitParameter(RequestParameter.AUDIO_FILES_DIRECTORY);
        File file = new File(rootPath + File.separator + relativePath);
        if(!file.exists()){
            file.mkdirs();
        }
        System.out.println("File Directory created to be used for storing files");
        ctx.setAttribute("FILES_DIR_FILE", file);
        ctx.setAttribute(RequestParameter.AUDIO_FILES_DIRECTORY, rootPath + File.separator + relativePath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
