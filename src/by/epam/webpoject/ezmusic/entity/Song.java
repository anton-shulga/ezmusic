package by.epam.webpoject.ezmusic.entity;

import java.sql.Date;
import java.util.ArrayList;


/**
 * Created by Антон on 28.07.2016.
 */
public class Song {
    private long songId;
    private String name;
    private int year;
    private double cost;
    private String filePath;
    private Date publicationDate;
    private ArrayList<Author> authorList;
    private ArrayList<Album> albumList;
    private ArrayList<Comment> commentList;

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public ArrayList<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(ArrayList<Author> authorList) {
        this.authorList = authorList;
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(ArrayList<Album> albumList) {
        this.albumList = albumList;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;

        Song song = (Song) o;

        if (songId != song.songId) return false;
        if (year != song.year) return false;
        if (Double.compare(song.cost, cost) != 0) return false;
        if (!name.equals(song.name)) return false;
        if (!filePath.equals(song.filePath)) return false;
        if (!publicationDate.equals(song.publicationDate)) return false;
        if (authorList != null ? !authorList.equals(song.authorList) : song.authorList != null) return false;
        if (albumList != null ? !albumList.equals(song.albumList) : song.albumList != null) return false;
        return commentList != null ? commentList.equals(song.commentList) : song.commentList == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (songId ^ (songId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + year;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + filePath.hashCode();
        result = 31 * result + publicationDate.hashCode();
        result = 31 * result + (authorList != null ? authorList.hashCode() : 0);
        result = 31 * result + (albumList != null ? albumList.hashCode() : 0);
        result = 31 * result + (commentList != null ? commentList.hashCode() : 0);
        return result;
    }
}
