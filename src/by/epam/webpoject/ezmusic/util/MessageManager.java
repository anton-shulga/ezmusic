package by.epam.webpoject.ezmusic.util;

import java.util.ResourceBundle;

/**
 * Created by Антон on 18.09.2016.
 */
public class MessageManager {
    private String locale;
    private ResourceBundle bundle;

    public MessageManager() {

    }



    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
