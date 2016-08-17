package by.epam.webpoject.ezmusic.dao.impl;

import by.epam.webpoject.ezmusic.connection.ConnectionPool;
import by.epam.webpoject.ezmusic.connection.ProxyConnection;
import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Антон on 15.08.2016.
 */
public class MySqlAuthorDAO extends AuthorDAO {

    private static final MySqlAuthorDAO instance = new MySqlAuthorDAO();
    private static final String CREATE_AUTHOR_QUERY = "INSERT INTO ezmusicdb.author (author_name, author_country, author_image_path) VALUES (?,?,?)";
    private static final String FIND_AUTHOR_QUERY = "SELECT author_id, author_name, author_country, author_image_path FROM ezmusicdb.author WHERE author_id = ?";
    private static final String DELETE_AUTHOR_QUERY = "DELETE FROM ezmusicdb.author WHERE author_id = ?";
    private static final String  UPDATE_AUTHOR_QUERY = "UPDATE ezmusicdb.author SET author_name = ?, author_country = ?, author_image_path = ? WHERE author_id = ?";
    private static final String FIND_ALL_AUTHORS_QUERY = "SELECT author_id, author_name, author_country, author_image_path FROM ezmusicdb.author";
    private static final String FIND_AUTHOR_BY_SONG_ID_QUERY = "SELECT author_id, author_name, author_country, author_image_path FROM ezmusicdb.author AS a INNER JOIN ezmusicdb.author_song AS a_s ON a.author_id = a_s.id_author WHERE a_s.id_song = ?";

    private MySqlAuthorDAO(){}

    public static MySqlAuthorDAO getInstance(){
        return instance;
    }

    @Override
    public Long create(Author instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Long generatedId = null;
        try {
            statement = connection.prepareStatement(CREATE_AUTHOR_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, instance.getName());
            statement.setString(2, instance.getCountry());
            statement.setString(3, instance.getPhotoPath());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Creating author error", e);
        }finally {
            closeStatement(statement);
                connection.close();
        }
        return generatedId;
    }

    @Override
    public Author find(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Author author = null;
        try {
            statement = connection.prepareStatement(FIND_AUTHOR_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                author = new Author();
                author.setAuthorId(resultSet.getLong(1));
                author.setName(resultSet.getString(2));
                author.setCountry(resultSet.getString(3));
                author.setPhotoPath(resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new DAOException("Finding author error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return author;
    }

    @Override
    public void delete(Long id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(DELETE_AUTHOR_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Deleting author error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public void update(Author instance) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_AUTHOR_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Updating author error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
    }

    @Override
    public ArrayList<Author> findAll() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ArrayList<Author> authorList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_AUTHORS_QUERY);
            Author author = null;
            while (resultSet.next()){
                author = new Author();
                author.setAuthorId(resultSet.getLong(1));
                author.setName(resultSet.getString(2));
                author.setCountry(resultSet.getString(3));
                author.setPhotoPath(resultSet.getString(4));
                authorList.add(author);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding author error", e);
        }finally {
            closeStatement(statement);
            connection.close();
        }
        return authorList;
    }

    @Override
    public ArrayList<Author> findBySongId(Long songId) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ArrayList<Author> authorList = new ArrayList<>();
        try{
            statement = connection.prepareStatement(FIND_AUTHOR_BY_SONG_ID_QUERY);
            statement.setLong(1, songId);
            ResultSet resultSet = statement.executeQuery();
            Author author = null;
            while (resultSet.next()){
                author = new Author();
                author.setAuthorId(resultSet.getLong(1));
                author.setName(resultSet.getString(2));
                author.setCountry(resultSet.getString(3));
                author.setPhotoPath(resultSet.getString(4));
                authorList.add(author);
            }
        } catch (SQLException e) {
            throw new DAOException("Finding author error", e);
        }
        return authorList;
    }
}
