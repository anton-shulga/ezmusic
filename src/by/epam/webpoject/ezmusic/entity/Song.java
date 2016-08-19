package by.epam.webpoject.ezmusic.entity;

import java.sql.Date;


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
        return publicationDate.equals(song.publicationDate);

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
        return result;
    }
}
