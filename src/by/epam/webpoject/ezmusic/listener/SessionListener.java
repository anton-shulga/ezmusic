package by.epam.webpoject.ezmusic.listener;

import by.epam.webpoject.ezmusic.constant.Locale;
import by.epam.webpoject.ezmusic.constant.RequestParameter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Антон on 04.08.2016.
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(RequestParameter.LOCALE, Locale.DEFAULT);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
