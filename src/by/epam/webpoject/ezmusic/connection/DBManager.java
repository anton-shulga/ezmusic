package by.epam.webpoject.ezmusic.connection;

import java.util.ResourceBundle;

/**
 * Created by Антон on 16.07.2016.
 */
public class DBManager {
    private final static DBManager instance = new DBManager();

    private DBManager(){}

    private ResourceBundle bundle = ResourceBundle.getBundle("property.db");

    public static DBManager getInstance(){
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
