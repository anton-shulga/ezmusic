package by.epam.webpoject.ezmusic.dao.factory;

import by.epam.webpoject.ezmusic.connection.DBManager;
import by.epam.webpoject.ezmusic.connection.DBParameter;
import by.epam.webpoject.ezmusic.dao.AbstractDAO;
import by.epam.webpoject.ezmusic.dao.impl.MySqlUserDAO;
import by.epam.webpoject.ezmusic.enumeration.type.DBType;

/**
 * Created by Антон on 21.07.2016.
 */
public class DAOFactory {

    private final static DBType DB_TYPE = DBType.valueOf(DBManager.getInstance().getValue(DBParameter.DB_TYPE).toUpperCase());

    public static AbstractDAO createUserDao(){
        switch (DB_TYPE){
            case MYSQL:
                return MySqlUserDAO.getInstance();
            default:
                return MySqlUserDAO.getInstance();
        }
    }

}
