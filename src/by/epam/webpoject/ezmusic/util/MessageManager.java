package by.epam.webpoject.ezmusic.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Антон on 18.09.2016.
 */
public class MessageManager {

    private Locale locale;
    private ResourceBundle bundle;

    public MessageManager() {

    }

    public static String getMessage(String  localeString, String bundlePath, String key){
        Locale.setDefault(new Locale("en", "US"));
        String[] languageAndLocale;
        languageAndLocale = localeString.split("_");
        Locale locale;
        if(languageAndLocale.length == 2) {
            locale = new Locale(languageAndLocale[0], languageAndLocale[1]);
        }else {
            locale = Locale.getDefault();
        }
        ResourceBundle bundle =  ResourceBundle.getBundle(bundlePath, locale);
        return bundle.getString(key);
    }

}
