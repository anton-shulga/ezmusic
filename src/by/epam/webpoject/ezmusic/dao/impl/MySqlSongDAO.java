package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.entity.Genre;
import by.epam.webpoject.ezmusic.entity.Reward;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.entity.Tag;
import by.epam.webpoject.ezmusic.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Антон on 04.08.2016.
 */
public class MySqlSongDAO implements SongDAO {
    private static final String CREATE_SONG_QUERY = "INSERT INTO ezmusicdb.song (song_name, song_year, song_file_path, song_publication_date, song_cost, song_genre_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_SONG_QUERY = "SELECT song_id, song_name, song_year, song_file_path, song_publication_date, song_cost, song_genre_id FROM ezmusicdb.song WHERE song_id = ?";
    private static final String DELETE_SONG_QUERY = "DELETE FROM ezmusicdb.song WHERE song_id = ?";
    private static final String UPDATE_SONG_QUERY = "UPDATE ezmusicdb.song SET song_name = ?, song_year = ?, song_file_path = ?, song_publication_date = ?, song_cost = ?, song_genre_id = ? WHERE song_id = ?";
    private static final String FIND_SONG_BY_USER_ID_QUERY = "SELECT s.song_id, s.song_name, s.song_year, s.song_file_path, s.song_publication_date, s.song_cost FROM ezmusicdb.song AS s INNER JOIN ezmusicdb.user_song as u_s ON s.song_id = u_s.id_song WHERE u_s.id_user = ?";
    private static final String FIND_SONG_BY_ALBUM_ID_QUERY = "SELECT s.song_id, s.song_name, s.song_year, s.song_file_path, s.song_publication_date, s.song_cost, s.song_genre_id FROM ezmusicdb.song AS s INNER JOIN ezmusicdb.album_song as a_s ON s.song_id = a_s.id_song WHERE a_s.id_album = ?";
    private static final String FIND_SONG_BY_AUTHOR_ID_QUERY = "SELECT s.song_id, s.song_name, s.song_year, s.song_file_path, s.song_publication_date, s.song_cost, s.song_genre_id FROM ezmusicdb.song AS s INNER JOIN ezmusicdb.author_song AS a_s ON s.song_id = a_s.id_song WHERE a_s.id_author = ?";
    private static final String FIND_SONG_BY_ORDER_ID_QUERY = "SELECT s.song_id, s.song_name, s.song_year, s.song_file_path, s.song_publication_date, s.song_cost, s.song_genre_id FROM ezmusicdb.song AS s INNER JOIN ezmusicdb.order_song AS o_s ON s.song_id = o_s.id_song WHERE o_s.id_order = ?";
    private static final String FIND_ALL_SONGS = "SELECT song_id, song_name, song_year, song_file_path, song_publication_date, song_cost, song_genre_id FROM ezmusicdb.song";
    private static final String CREATE_SONG_ALBUM_QUERY = "INSERT INTO ezmusicdb.album_song (id_album, id_song) VALUES (?, ?)";
    private static final String CREATE_SONG_AUTHOR_QUERY = "INSERT INTO ezmusicdb.author_song (id_author, id_song) VALUES (?, ?)";
    private static final String DELETE_SONG_ALBUM_QUERY = "DELETE FROM ezmusicdb.album_song WHERE id_song = ?";
    private static final String DELETE_SONG_AUTHOR_QUERY = "DELETE FROM ezmusicdb.author_song WHERE id_song = ?";
    private static final String CREATE_SONG_ORDER_QUERY = "INSERT INTO ezmusicdb.order_song (id_order, id_song) VALUES (?, ?)";
    private static final String DELETE_SONG_ORDER_QUERY = "DELETE FROM ezmusicdb.order_song WHERE id_order = ? AND id_song = ?";
    private static final MySqlSongDAO instance = new MySqlSongDAO();
    private static final String FIND_SONG_BY_SEARCH_REQUEST_QUERY = "SELECT song_id, song_name, song_year, song_file_path, song_publication_date, song_cost FROM ezmusicdb.song WHERE song_name LIKE ?";
    private static final String IS_ORDERED_SONG_QUERY = "SELECT o.order_id, o.user_id, o.order_is_paid, o.order_total_cost FROM ezmusicdb.order AS o INNER JOIN ezmusicdb.order_song AS o_s ON o.order_id = o_s.id_order WHERE o_s.id_song = ? AND o.order_is_paid = TRUE";


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
            statement.setLong(6, instance.getGenre().getGenreId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Create song DAO exception", e);
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
                Genre genre = new Genre();
                genre.setGenreId(resultSet.getLong(7));
                song.setGenre(genre);
            }
        } catch (SQLException e) {
            throw new DAOException("Find song DAO exception", e);
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
            throw new DAOException("Delete song DAO exception", e);
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
            statement.setLong(6, instance.getGenre().getGenreId());
            statement.setLong(7, instance.getSongId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Update song DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }

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
                Genre genre = new Genre();
                genre.setGenreId(resultSet.getLong(7));
                song.setGenre(genre);
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Find songs by album id DAO exception", e);
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
                Genre genre = new Genre();
                genre.setGenreId(resultSet.getLong(7));
                song.setGenre(genre);
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Find songs by author id DAO exception", e);
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
                Genre genre = new Genre();
                genre.setGenreId(resultSet.getLong(7));
                song.setGenre(genre);
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Find songs by order id DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return songList;
    }

    @Override
    public ArrayList<Song> findAll() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Song> songList = null;
        try {
            statement = connection.prepareStatement(FIND_ALL_SONGS);
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
                Genre genre = new Genre();
                genre.setGenreId(resultSet.getLong(7));
                song.setGenre(genre);
                songList.add(song);
            }
        } catch (SQLException e) {
            throw new DAOException("Find all songs DAO exception", e);
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
            throw new DAOException("Create song album DAO exception", e);
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
            throw new DAOException("Create song author DAO exception", e);
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
            throw new DAOException("Create song order DAO exception", e);
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
            throw new DAOException("Delete song album DAO exception", e);
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
            throw new DAOException("Delete song author DAO exception", e);
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


    public boolean isOrderedSong(Long songId) throws DAOException {
        PreparedStatement statement = null;
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try {
            statement = connection.prepareStatement(IS_ORDERED_SONG_QUERY);
            statement.setLong(1, songId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Is ordered song DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return false;
    }


    public ArrayList<Tag> findAllTags() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Tag> tagList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT tag_id, tag_name FROM ezmusicdb.tag");
            ResultSet resultSet = statement.executeQuery();
            Tag tag = null;
            while (resultSet.next()){
                tag = new Tag();
                tag.setTagId(resultSet.getLong(1));
                tag.setName(resultSet.getString(2));

                tagList.add(tag);
            }
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return tagList;
    }


    public void createTagSong(Long tagId, Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO ezmusicdb.song_tag (tag_id, song_id) VALUES (?, ?)");
            statement.setLong(1, tagId);
            statement.setLong(2, songId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }

    public void deleteTagSong( Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM ezmusicdb.song_tag WHERE song_id = ?");
            statement.setLong(1, songId);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }





    public ArrayList<Reward> findAllSongRewards() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Reward> rewardList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT song_reward, song_reward_name, song_reward_year FROM ezmusicdb.song_reward");
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


    public void createRewardSong(Long rewardId, Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO ezmusicdb.reward_song (song_id, reward_id) VALUES (?, ?)");
            statement.setLong(1, songId);
            statement.setLong(2, rewardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }





    public void deleteRewardSong(Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM ezmusicdb.reward_song WHERE song_id = ?");
            statement.setLong(1, songId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Delete song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
    }



    @Override
    public ArrayList<Genre> findAllGenres() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Genre> genreList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT genre_id, genre_name FROM ezmusicdb.genre");
            ResultSet resultSet = statement.executeQuery();
            Genre genre = null;
            while (resultSet.next()){
                genre = new Genre();
                genre.setGenreId(resultSet.getLong(1));
                genre.setName(resultSet.getString(2));

                genreList.add(genre);
            }
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return genreList;
    }

    @Override
    public ArrayList<Tag> findTagsBySongId(Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Tag> tagList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT t.tag_id, t.tag_name FROM ezmusicdb.tag AS t INNER JOIN ezmusicdb.song_tag AS s_t ON s_t.tag_id = t.tag_id WHERE s_t.song_id = ?");
            statement.setLong(1, songId);
            ResultSet resultSet = statement.executeQuery();
            Tag tag = null;
            while (resultSet.next()){
                tag = new Tag();
                tag.setTagId(resultSet.getLong(1));
                tag.setName(resultSet.getString(2));

                tagList.add(tag);
            }
        } catch (SQLException e) {
            throw new DAOException("Create song author DAO exception", e);
        } finally {
            closeStatement(statement);
            connection.close();
        }
        return tagList;
    }

    @Override
    public ArrayList<Reward> findRewardsBySongId(Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Reward> rewardList = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT s_r.song_reward, s_r.song_reward_name, s_r.song_reward_year FROM ezmusicdb.song_reward AS s_r INNER JOIN ezmusicdb.reward_song AS r_s ON r_s.reward_id = s_r.song_reward WHERE r_s.song_id =?");
            statement.setLong(1, songId);
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