package by.epam.webpoject.ezmusic.dao.factory;

import by.epam.webpoject.ezmusic.connection.DBManager;
import by.epam.webpoject.ezmusic.connection.DBParameter;
import by.epam.webpoject.ezmusic.dao.AbstractDAO;
import by.epam.webpoject.ezmusic.dao.impl.*;
import by.epam.webpoject.ezmusic.enumeration.DBType;

/**
 * Created by Антон on 21.07.2016.
 */
public class DAOFactory {

    private final static DBType DB_TYPE = DBType.valueOf(DBManager.getInstance().getValue(DBParameter.DB_TYPE).toUpperCase());

    public static AbstractDAO createUserDAO() {
        switch (DB_TYPE) {
            case MYSQL:
                return MySqlUserDAO.getInstance();
            //case ORACLE:...
            default:
                return MySqlUserDAO.getInstance();
        }
    }

    public static AbstractDAO createSongDAO() {
        switch (DB_TYPE) {
            case MYSQL:
                return MySqlSongDAO.getInstance();
            default:
                return MySqlSongDAO.getInstance();
        }
    }

    public static AbstractDAO createAlbumDAO() {
        switch (DB_TYPE) {
            case MYSQL:
                return MySqlAlbumDAO.getInstance();
            default:
                return MySqlAlbumDAO.getInstance();
        }
    }

    public static AbstractDAO createCommentDAO() {
        switch (DB_TYPE) {
            case MYSQL:
                return MySqlCommentDAO.getInstance();
            default:
                return MySqlCommentDAO.getInstance();
        }
    }

    public static AbstractDAO createAuthorDAO() {
        switch (DB_TYPE) {
            case MYSQL:
                return MySqlAuthorDAO.getInstance();
            default:
                return MySqlAuthorDAO.getInstance();
        }
    }

    public static AbstractDAO createOrderDAO() {
        switch (DB_TYPE) {
            case MYSQL:
                return MySqlOrderDAO.getInstance();
            default:
                return MySqlOrderDAO.getInstance();
        }
    }
}
