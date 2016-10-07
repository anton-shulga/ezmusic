package by.epam.webpoject.ezmusic.connection;

import by.epam.webpoject.ezmusic.constant.FilePath;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Антон on 16.07.2016.
 */
public class DBManager {
    private final static DBManager instance = new DBManager();
    private ResourceBundle bundle = null;

    private DBManager() {

    }

    public void initialize() throws MissingResourceException {
        bundle = ResourceBundle.getBundle(FilePath.DB_CONFIGURATION);
    }

    public static DBManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
