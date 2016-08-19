package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Антон on 07.08.2016.
 */
public class MySqlAlbumDAO extends AlbumDAO {

    private static final MySqlAlbumDAO instance = new MySqlAlbumDAO();
    private static final String CREATE_ALBUM_QUERY = "INSERT INTO ezmusicdb.album (album_name, album_year, album_image_path) VALUES (?, ?, ?)";
    private static final String FIND_ALBUM_QUERY = "SELECT album_id, album_name, album_year, album_image_path FROM ezmusicdb.album WHERE album_id = ?";
    private static final String DELETE_ALBUM_QUERY = "DELETE FROM ezmusicdb.album WHERE album_id = ?";
    private static final String UPDATE_ALBUM_QUERY = "UPDATE ezmusicdb.album SET album_name = ?, album_year = ?, album_image_path = ? WHERE album_id = ?" ;
    private static final String FIND_ALBUM_BY_AUTHOR_ID_QUERY = "SELECT album_id, album_name, album_year, album_image_path FROM ezmusicdb.album AS a INNER JOIN ezmusicdb.album_author as a_a on a.album_id = a_a.id_album where a_a.id_author = ?";
    private static final String FIND_ALL_ALBUMS = "SELECT  album_id, album_name, album_year, album_image_path FROM ezmusicdb.album";
    private static final String  FIND_ALBUM_BY_SONG_ID_QUERY = "SELECT album_id, album_name, album_year, album_image_path FROM ezmusicdb.album AS a INNER JOIN ezmusicdb.album_song AS a_s ON a.album_id = a_s.id_album WHERE a_s.id_song = ?";
    private static final String CREATE_ALBUM_SONG_QUERY = "INSERT  INTO ezmusicdb.album_song (id_album, id_song) VALUES (?, ?)";
    private static final String CREATE_ALBUM_AUTHOR_QUERY = "INSERT INTO ezmusicdb.album_author (id_author, id_album) VALUES (?, ?)";
    private static final String DELETE_ALBUM_SONG_QUERY = "DELETE FROM ezmusicdb.album_song WHERE id_album =?";
    private static final String DELETE_ALBUM_AUTHOR_QUERY = "DELETE FROM ezmusicdb.album_author WHERE id_album = ?";

    private MySqlAlbumDAO(){}

    public static MySqlAlbumDAO getInstance(){
        return instance;
    }

    @Override
    public Long create(Album instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Long generatedId = null;
        try {
            statement = connection.prepareStatement(CREATE_ALBUM_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, instance.getName());
            statement.setInt(2, instance.getYear());
            statement.setString(3, instance.getImageFilePath());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Creating album error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return generatedId;
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
        }finally {
            closeStatement(statement);
            connection.close();
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
            throw new DAOException("Finding albums error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return albumList;
    }

    @Override
    public ArrayList<Album> findAll() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ArrayList<Album> albumList = new ArrayList<>();
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ALBUMS);
            Album album = null;
            while (resultSet.next()){
                album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding albums error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return albumList;
    }

    @Override
    public ArrayList<Album> findBySongId(Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Album> albumList =  new ArrayList<>();
        try{
            statement = connection.prepareStatement(FIND_ALBUM_BY_SONG_ID_QUERY);
            statement.setLong(1, songId);
            ResultSet resultSet = statement.executeQuery();
            Album album = null;
            while(resultSet.next()){
                album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding album error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return albumList;
    }

    @Override
    public boolean createAlbumSong(Long albumId, Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(CREATE_ALBUM_SONG_QUERY,  PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, albumId);
            statement.setLong(2, songId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Creating album song error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }

    @Override
    public boolean createAlbumAuthor(Long albumId, Long authorId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(CREATE_ALBUM_AUTHOR_QUERY,  PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, authorId);
            statement.setLong(2, albumId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Creating album author error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }

    @Override
    public void deleteAlbumSong(Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(DELETE_ALBUM_SONG_QUERY);
            statement.setLong(1, albumId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Deleting album song error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void deleteAlbumAuthor(Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(DELETE_ALBUM_AUTHOR_QUERY);
            statement.setLong(1, albumId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Deleting album author error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }
}
