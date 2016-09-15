package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Антон on 04.08.2016.
 */
public class MySqlSongDAO implements SongDAO {
    private static final String CREATE_SONG_QUERY = "INSERT INTO ezmusicdb.song (song_name, song_year, song_file_path, song_publication_date, song_cost) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_SONG_QUERY = "SELECT song_id, song_name, song_year, song_file_path, song_publication_date, song_cost FROM ezmusicdb.song WHERE song_id = ?";
    private static final String DELETE_SONG_QUERY = "DELETE FROM ezmusicdb.song WHERE song_id = ?";
    private static final String UPDATE_SONG_QUERY = "UPDATE ezmusicdb.song SET song_name = ?, song_year = ?, song_file_path = ?, song_publication_date = ?, song_cost = ? WHERE song_id = ?";
    private static final String FIND_SONG_BY_USER_ID_QUERY = "SELECT s.song_id, s.song_name, s.song_year, s.song_file_path, s.song_publication_date, s.song_cost FROM ezmusicdb.song AS s INNER JOIN ezmusicdb.user_song as u_s ON s.song_id = u_s.id_song WHERE u_s.id_user = ?";
    private static final String FIND_SONG_BY_ALBUM_ID_QUERY = "SELECT s.song_id, s.song_name, s.song_year, s.song_file_path, s.song_publication_date, s.song_cost FROM ezmusicdb.song AS s INNER JOIN ezmusicdb.album_song as a_s ON s.song_id = a_s.id_song WHERE a_s.id_album = ?";
    private static final String FIND_SONG_BY_AUTHOR_ID_QUERY = "SELECT s.song_id, s.song_name, s.song_year, s.song_file_path, s.song_publication_date, s.song_cost FROM ezmusicdb.song AS s INNER JOIN ezmusicdb.author_song AS a_s ON s.song_id = a_s.id_song WHERE a_s.id_author = ?";
    private static final String FIND_SONG_BY_ORDER_ID_QUERY = "SELECT s.song_id, s.song_name, s.song_year, s.song_file_path, s.song_publication_date, s.song_cost FROM ezmusicdb.song AS s INNER JOIN ezmusicdb.order_song AS o_s ON s.song_id = o_s.id_song WHERE o_s.id_order = ?";
    private static final String FIND_ALL_SONGS = "SELECT song_id, song_name, song_year, song_file_path, song_publication_date, song_cost FROM ezmusicdb.song";
    private static final String CREATE_SONG_ALBUM_QUERY = "INSERT INTO ezmusicdb.album_song (id_album, id_song) VALUES (?, ?)";
    private static final String CREATE_SONG_AUTHOR_QUERY = "INSERT INTO ezmusicdb.author_song (id_author, id_song) VALUES (?, ?)";
    private static final String DELETE_SONG_ALBUM_QUERY = "DELETE FROM ezmusicdb.album_song WHERE id_song = ?";
    private static final String DELETE_SONG_AUTHOR_QUERY = "DELETE FROM ezmusicdb.author_song WHERE id_song = ?";
    private static final String CREATE_SONG_ORDER_QUERY = "INSERT INTO ezmusicdb.order_song (id_order, id_song) VALUES (?, ?)";
    private static final String DELETE_SONG_ORDER_QUERY = "DELETE FROM ezmusicdb.order_song WHERE id_order = ? AND id_song = ?";
    private static final MySqlSongDAO instance = new MySqlSongDAO();
    private static final String FIND_SONG_BY_SEARCH_REQUEST_QUERY = "SELECT song_id, song_name, song_year, song_file_path, song_publication_date, song_cost FROM ezmusicdb.song WHERE song_name LIKE ?";


    private MySqlSongDAO() {
    }

    public static MySqlSongDAO getInstance() {
        return instance;
    }


    @Override
    public Long create(Song instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Long generatedId = null;
        try {
            statement = connection.prepareStatement(CREATE_SONG_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, instance.getName());
            statement.setInt(2, instance.getYear());
            statement.setString(3, instance.getFilePath());
            statement.setDate(4, instance.getPublicationDate());
            statement.setDouble(5, instance.getCost());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Creating song error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return generatedId;
    }

    @Override
    public Song find(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Song song = null;
        try {
            statement = connection.prepareStatement(FIND_SONG_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                song = new Song();
                song.setSongId(resultSet.getLong(1));
                song.setName(resultSet.getString(2));
                song.setYear(resultSet.getInt(3));
                song.setFilePath(resultSet.getString(4));
                song.setPublicationDate(resultSet.getDate(5));
                song.setCost(resultSet.getDouble(6));
            }
        } catch (SQLException e) {
            throw new DAOException("Finding song error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return song;
    }

    @Override
    public void delete(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_SONG_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Deleting song error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void update(Song instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_SONG_QUERY);
            statement.setString(1, instance.getName());
            statement.setInt(2, instance.getYear());
            statement.setString(3, instance.getFilePath());
            statement.setDate(4, instance.getPublicationDate());
            statement.setDouble(5, instance.getCost());
            statement.setLong(6, instance.getSongId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Updating song error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }

    }

    @Override
    public ArrayList<Song> findByUserId(Long userId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Song> songList = null;
        try {
            statement = connection.prepareStatement(FIND_SONG_BY_USER_ID_QUERY);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            songList = new ArrayList<>();
            while (resultSet.next()) {
                Song song = new Song();
                song.setSongId(resultSet.getLong(1));
                song.setName(resultSet.getString(2));
                song.setYear(resultSet.getInt(3));
                song.setFilePath(resultSet.getString(4));
                song.setPublicationDate(resultSet.getDate(5));
                song.setCost(resultSet.getDouble(6));
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding song error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return songList;
    }

    @Override
    public ArrayList<Song> findByAlbumId(Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Song> songList = null;
        try {
            statement = connection.prepareStatement(FIND_SONG_BY_ALBUM_ID_QUERY);
            statement.setLong(1, albumId);
            ResultSet resultSet = statement.executeQuery();
            songList = new ArrayList<>();
            while (resultSet.next()) {
                Song song = new Song();
                song.setSongId(resultSet.getLong(1));
                song.setName(resultSet.getString(2));
                song.setYear(resultSet.getInt(3));
                song.setFilePath(resultSet.getString(4));
                song.setPublicationDate(resultSet.getDate(5));
                song.setCost(resultSet.getDouble(6));
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding song error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return songList;
    }

    @Override
    public ArrayList<Song> findByAuthorId(Long authorId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Song> songList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_SONG_BY_AUTHOR_ID_QUERY);
            statement.setLong(1, authorId);
            ResultSet resultSet = statement.executeQuery();
            Song song = null;
            while (resultSet.next()) {
                song = new Song();
                song.setSongId(resultSet.getLong(1));
                song.setName(resultSet.getString(2));
                song.setYear(resultSet.getInt(3));
                song.setFilePath(resultSet.getString(4));
                song.setPublicationDate(resultSet.getDate(5));
                song.setCost(resultSet.getDouble(6));
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Find song dao exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return songList;
    }

    @Override
    public ArrayList<Song> findByOrderId(Long orderId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Song> songList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_SONG_BY_ORDER_ID_QUERY);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            Song song = null;
            while (resultSet.next()) {
                song = new Song();
                song.setSongId(resultSet.getLong(1));
                song.setName(resultSet.getString(2));
                song.setYear(resultSet.getInt(3));
                song.setFilePath(resultSet.getString(4));
                song.setPublicationDate(resultSet.getDate(5));
                song.setCost(resultSet.getDouble(6));
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding song error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return songList;
    }

    @Override
    public ArrayList<Song> findAll() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ArrayList<Song> songList = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SONGS);
            songList = new ArrayList<>();
            while (resultSet.next()) {
                Song song = new Song();
                song.setSongId(resultSet.getLong(1));
                song.setName(resultSet.getString(2));
                song.setYear(resultSet.getInt(3));
                song.setFilePath(resultSet.getString(4));
                song.setPublicationDate(resultSet.getDate(5));
                song.setCost(resultSet.getDouble(6));
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding song error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return songList;
    }

    @Override
    public boolean createSongAlbum(Long songId, Long albumId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_SONG_ALBUM_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, albumId);
            statement.setLong(2, songId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Creating song-album error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }

    @Override
    public boolean createSongAuthor(Long songId, Long authorId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_SONG_AUTHOR_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, authorId);
            statement.setLong(2, songId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Creating song-author error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }

    @Override
    public boolean createSongOrder(Long songId, Long orderId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_SONG_ORDER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, orderId);
            statement.setLong(2, songId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Creating song-author error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }

    @Override
    public void deleteSongAlbum(Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_SONG_ALBUM_QUERY);
            statement.setLong(1, songId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Deleting song-album error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void deleteSongAuthor(Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_SONG_AUTHOR_QUERY);
            statement.setLong(1, songId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Deleting song-author error", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void deleteSongOrder(Long songId, Long orderId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_SONG_ORDER_QUERY);
            statement.setLong(1, orderId);
            statement.setLong(2, songId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete song order DAO exception");
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public ArrayList<Song> findBySearchRequest(String searchRequest) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Song> songList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(FIND_SONG_BY_SEARCH_REQUEST_QUERY);
            statement.setString(1, "%" + searchRequest + "%");
            ResultSet resultSet = statement.executeQuery();
            Song song = null;
            while (resultSet.next()) {
                song = new Song();
                song.setSongId(resultSet.getLong(1));
                song.setName(resultSet.getString(2));
                song.setYear(resultSet.getInt(3));
                song.setFilePath(resultSet.getString(4));
                song.setPublicationDate(resultSet.getDate(5));
                song.setCost(resultSet.getDouble(6));
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Find song by search request DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return songList;
    }
}