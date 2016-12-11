package by.epam.webpoject.ezmusic.service.author;

import by.epam.webpoject.ezmusic.dao.AuthorDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Label;
import by.epam.webpoject.ezmusic.exception.DAOException;
import by.epam.webpoject.ezmusic.exception.ServiceException;

import java.util.ArrayList;

/**
 * Created by Anton Shulha on 12.12.2016.
 */
public class FindAllLabelsService {
    public static ArrayList<Label> find() throws ServiceException {
        AuthorDAO authorDAO = (AuthorDAO) DAOFactory.createAuthorDAO();
        try {
            return authorDAO.findAllLabels();
        } catch (DAOException e) {
            throw new ServiceException("Find all labels service exception", e);
        }
    }
}
