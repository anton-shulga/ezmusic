package by.epam.webpoject.ezmusic.service.order;

import by.epam.webpoject.ezmusic.dao.OrderDAO;
import by.epam.webpoject.ezmusic.dao.SongDAO;
import by.epam.webpoject.ezmusic.dao.factory.DAOFactory;
import by.epam.webpoject.ezmusic.entity.Album;
import by.epam.webpoject.ezmusic.entity.Author;
import by.epam.webpoject.ezmusic.entity.Order;
import by.epam.webpoject.ezmusic.entity.Song;
import by.epam.webpoject.ezmusic.exception.dao.DAOException;
import by.epam.webpoject.ezmusic.exception.service.ServiceException;
import by.epam.webpoject.ezmusic.service.album.FindAlbumsBySongIdService;
import by.epam.webpoject.ezmusic.service.author.FindAuthorsBySongIdService;

import java.util.ArrayList;

/**
 * Created by Антон on 01.09.2016.
 */
public class FindCartByUserIdService {
    public static Order find(Long userId) throws ServiceException {
        OrderDAO orderDAO = (OrderDAO) DAOFactory.createOrderDAO();
        SongDAO songDAO = (SongDAO) DAOFactory.createSongDAO();
        try {
            Order cart = orderDAO.findCartByUserId(userId);
            ArrayList<Song> songList = songDAO.findByOrderId(cart.getOrderId());
            ArrayList<Author> songAuthors = null;
            ArrayList<Album> songAlbums = null;
            for(Song song : songList){
                songAuthors  = FindAuthorsBySongIdService.find(song.getSongId());
                songAlbums = FindAlbumsBySongIdService.find(song.getSongId());
                song.setAuthorList(songAuthors);
                song.setAlbumList(songAlbums);
            }
            cart.setSongList(songList);
            double totalCost = 0;
            for(Song song : cart.getSongList()){
                totalCost += song.getCost();
            }
            cart.setTotalCost(totalCost);
            return cart;
        } catch (DAOException e) {
            throw new ServiceException("Find cart service exception", e);
        }
    }
}
