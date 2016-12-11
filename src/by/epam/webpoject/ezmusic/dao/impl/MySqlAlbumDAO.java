package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.AlbumDAO;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.AlbumType;
import by.epam.webpoject.ezmusic.entity.Reward;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Антон on 07.08.2016.
 */
public class MySqlAlbumDAO implements AlbumDAO {
    private static final String CREATE_ALBUM_QUERY = "INSERT INTO ezmusicdb.album (album_name, album_year, album_image_path, album_type_id) VALUES (?, ?, ?, ?)";
    private static final String FIND_ALBUM_QUERY = "SELECT album_id, album_name, album_year, album_image_path, album_type_id FROM ezmusicdb.album WHERE album_id = ?";
    private static final String DELETE_ALBUM_QUERY = "DELETE FROM ezmusicdb.album WHERE album_id = ?";
    private static final String UPDATE_ALBUM_QUERY = "UPDATE ezmusicdb.album SET album_name = ?, album_year = ?, album_image_path = ?, album_type_id = ? WHERE album_id = ?";
    private static final String FIND_ALBUM_BY_AUTHOR_ID_QUERY = "SELECT album_id, album_name, album_year, album_image_path, album_type_id FROM ezmusicdb.album AS a INNER JOIN ezmusicdb.album_author as a_a on a.album_id = a_a.id_album where a_a.id_author = ?";
    private static final String FIND_ALL_ALBUMS = "SELECT  album_id, album_name, album_year, album_image_path, album_type_id FROM ezmusicdb.album";
    private static final String FIND_ALBUM_BY_SONG_ID_QUERY = "SELECT album_id, album_name, album_year, album_image_path, album_type_id FROM ezmusicdb.album AS a INNER JOIN ezmusicdb.album_song AS a_s ON a.album_id = a_s.id_album WHERE a_s.id_song = ?";
    private static final String CREATE_ALBUM_SONG_QUERY = "INSERT  INTO ezmusicdb.album_song (id_album, id_song) VALUES (?, ?)";
    private static final String CREATE_ALBUM_AUTHOR_QUERY = "INSERT INTO ezmusicdb.album_author (id_author, id_album) VALUES (?, ?)";
    private static final String DELETE_ALBUM_SONG_QUERY = "DELETE FROM ezmusicdb.album_song WHERE id_album =?";
    private static final String DELETE_ALBUM_AUTHOR_QUERY = "DELETE FROM ezmusicdb.album_author WHERE id_album = ?";
    private static final String FIND_ALBUM_BY_SEARCH_REQUEST_QUERY = "SELECT  album_id, album_name, album_year, album_image_path FROM ezmusicdb.album WHERE album_name LIKE ?";

    private static final MySqlAlbumDAO instance = new MySqlAlbumDAO();


    private MySqlAlbumDAO() {
    }

    public static MySqlAlbumDAO getInstance() {
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
            statement.setLong(4, instance.getAlbumType().getAlbumTypeId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Create album DAO exception", e);
        } finally {
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
            if (resultSet.next()) {
                album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
                AlbumType albumType = new AlbumType();
                albumType.setAlbumTypeId(resultSet.getLong(5));
                album.setAlbumType(albumType);
            }
        } catch (SQLException e) {
            throw new DAOException("Find album DAO exception", e);
        } finally {
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
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete album DAO exception", e);
        } finally {
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
            statement.setLong(4, instance.getAlbumType().getAlbumTypeId());
            statement.setLong(5, instance.getAlbumId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Update album DAO exception", e);
        } finally {
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
            while (resultSet.next()) {
                Album album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
                AlbumType albumType = new AlbumType();
                albumType.setAlbumTypeId(resultSet.getLong(5));
                album.setAlbumType(albumType);
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException("Find album by author id DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return albumList;
    }

    @Override
    public ArrayList<Album> findAll() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Album> albumList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_ALL_ALBUMS);
            ResultSet resultSet = statement.executeQuery();
            Album album = null;
            while (resultSet.next()) {
                album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
                AlbumType albumType = new AlbumType();
                albumType.setAlbumTypeId(resultSet.getLong(5));
                album.setAlbumType(albumType);
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException("Find all albums DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return albumList;
    }

    @Override
    public ArrayList<Album> findBySongId(Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Album> albumList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_ALBUM_BY_SONG_ID_QUERY);
            statement.setLong(1, songId);
            ResultSet resultSet = statement.executeQuery();
            Album album = null;
            while (resultSet.next()) {
                album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
                AlbumType albumType = new AlbumType();
                albumType.setAlbumTypeId(resultSet.getLong(5));
                album.setAlbumType(albumType);
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException("Find album by song id DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return albumList;
    }

    @Override
    public boolean createAlbumSong(Long albumId, Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_ALBUM_SONG_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, albumId);
            statement.setLong(2, songId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Create album song DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }

    @Override
    public boolean createAlbumAuthor(Long albumId, Long authorId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_ALBUM_AUTHOR_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, authorId);
            statement.setLong(2, albumId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Create album author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }

    @Override
    public void deleteAlbumSong(Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ALBUM_SONG_QUERY);
            statement.setLong(1, albumId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete album song DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void deleteAlbumAuthor(Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ALBUM_AUTHOR_QUERY);
            statement.setLong(1, albumId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete album author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public ArrayList<Album> findBySearchRequest(String searchRequest) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Album> albumList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_ALBUM_BY_SEARCH_REQUEST_QUERY);
            statement.setString(1, "%" + searchRequest + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album();
                album.setAlbumId(resultSet.getLong(1));
                album.setName(resultSet.getString(2));
                album.setYear(resultSet.getInt(3));
                album.setImageFilePath(resultSet.getString(4));
                albumList.add(album);
            }
        } catch (SQLException e) {
            throw new DAOException("Find album by search request DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return albumList;
    }



    @Override
    public ArrayList<AlbumType> findAllAlbumTypes() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<AlbumType> typeList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT album_type_id, album_type_name FROM ezmusicdb.album_type");
            ResultSet resultSet = statement.executeQuery();
            AlbumType albumType = null;
            while (resultSet.next()){
                albumType = new AlbumType();
                albumType.setAlbumTypeId(resultSet.getLong(1));
                albumType.setName(resultSet.getString(2));
                typeList.add(albumType);
            }
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return typeList;
    }

    public ArrayList<Reward> findAllAlbumReward() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Reward> rewardList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT album_reward_id, album_reward_name, album_reward_year FROM ezmusicdb.album_reward");
            ResultSet resultSet = statement.executeQuery();
            Reward reward = null;
            while (resultSet.next()){
                reward = new Reward();
                reward.setRewardId(resultSet.getLong(1));
                reward.setName(resultSet.getString(2));
                reward.setYear(resultSet.getInt(3));
                rewardList.add(reward);
            }
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return rewardList;
    }

    public void deleteRewardAlbum(Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM ezmusicdb.reward_album WHERE album_id = ?");
            statement.setLong(1, albumId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    public void createRewardAlbum(Long rewardId, Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO ezmusicdb.reward_album (album_id, reward_id) VALUES (?, ?)");
            statement.setLong(1, albumId);
            statement.setLong(2, rewardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public ArrayList<Reward> findRewardsByAlbumId(Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Reward> rewardList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT a_r.album_reward_id, a_r.album_reward_name, a_r.album_reward_year FROM ezmusicdb.album_reward AS a_r INNER JOIN ezmusicdb.reward_album AS r_a ON r_a.reward_id = a_r.album_reward_id WHERE r_a.album_id =?");
            statement.setLong(1, albumId);
            ResultSet resultSet = statement.executeQuery();
            Reward reward = null;
            while (resultSet.next()){
                reward = new Reward();
                reward.setRewardId(resultSet.getLong(1));
                reward.setName(resultSet.getString(2));
                reward.setYear(resultSet.getInt(3));
                rewardList.add(reward);
            }
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return rewardList;
    }

}
