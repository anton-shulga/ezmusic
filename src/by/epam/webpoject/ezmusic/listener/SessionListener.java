package by.epam.webpoject.ezmusic.listener;

import by.epam.webpoject.ezmusic.constant.Locale;
import by.epam.webpoject.ezmusic.constant.RequestParameter;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

/**
 * Created by Антон on 04.08.2016.
 */
public class SessionListener implements HttpSessionListener {
    private ArrayList<HttpSession> sessions = new ArrayList<>();
    private  int totalSessionNumber = 0;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessions.add(httpSessionEvent.getSession());
        totalSessionNumber++;
        String locate = (String) httpSessionEvent.getSession().getAttribute(RequestParameter.LOCALE);
        if(locate == null) {
            httpSessionEvent.getSession().setAttribute(RequestParameter.LOCALE, Locale.DEFAULT);
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        sessions.remove(httpSessionEvent.getSession());
        totalSessionNumber--;
    }
}
