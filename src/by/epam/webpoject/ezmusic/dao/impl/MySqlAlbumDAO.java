package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Антон on 07.08.2016.
 */
public class MySqlAlbumDAO extends AlbumDAO {

    private static final Logger LOGGER = LogManager.getLogger(MySqlAlbumDAO.class);
    private static final MySqlAlbumDAO instance = new MySqlAlbumDAO();
    private static final String CREATE_ALBUM_QUERY = "INSERT INTO ALBUM (album_name, album_year, album_image_path) VALUES (?, ?, ?)";
    private static final String FIND_ALBUM_QUERY = "SELECT album_id, album_name, album_year, album_image_path FROM ALBUM WHERE album_id = ?";
    private static final String DELETE_ALBUM_QUERY = "DELETE FROM ALBUM WHERE album_id = ?";
    private static final String UPDATE_ALBUM_QUERY = "UPDATE ALBUM SET album_name = ?, album_year = ?, album_image_path = ? WHERE album_id = ?" ;
    private static final String FIND_ALBUM_BY_AUTHOR_ID_QUERY = "SELECT album_id, album_name, album_year, album_image_path FROM ALBUM AS a INNER JOIN ALBUM_AUTHOR as a_a on a_a.id_album = a_a.album_id where a_a.id_author = ?";

    private MySqlAlbumDAO(){}

    public static MySqlAlbumDAO getInstance(){
        return instance;
    }

    @Override
    public boolean create(Album instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_ALBUM_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, instance.getName());
            statement.setInt(2, instance.getYear());
            statement.setString(3, instance.getImageFilePath());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Creating album error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }

    @Override
    public Album find(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Album album = null;
        try {
            statement = connection.prepareStatement(FIND_ALBUM_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new DAOException("Finding album error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return album;
    }

    @Override
    public void delete(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ALBUM_QUERY);
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Deleting album error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void update(Album instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_ALBUM_QUERY);
            statement.setString(1, instance.getName());
            statement.setInt(2, instance.getYear());
            statement.setString(3, instance.getImageFilePath());
            statement.setLong(4, instance.getAlbumId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Updating album error", e);
        }
    }

    @Override
    public ArrayList<Album> findByAuthorId(Long authorId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Album> albumList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_ALBUM_BY_AUTHOR_ID_QUERY);
            statement.setLong(1, authorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Album album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding album error", e);
        }
        return albumList;
    }

}
