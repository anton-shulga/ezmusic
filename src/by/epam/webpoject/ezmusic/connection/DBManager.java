package by.epam.webpoject.ezmusic.connection;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Антон on 16.07.2016.
 */
public class DBManager {
    private final static DBManager instance = new DBManager();
    private ResourceBundle bundle = null;
    private DBManager(){
        initialize();
    }
    private void initialize(){
        try {
          bundle = ResourceBundle.getBundle("property.db");
        }catch (MissingResourceException e){

        }
    }

    public static DBManager getInstance(){
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
