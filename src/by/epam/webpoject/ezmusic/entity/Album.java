package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Антон on 28.07.2016.
 */
public class Album {
    private long albumId;
    private String name;
    private int year;
    private String imageFilePath;

    public Album() {
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
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

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;

        Album album = (Album) o;

        if (albumId != album.albumId) return false;
        if (year != album.year) return false;
        if (!name.equals(album.name)) return false;
        return imageFilePath.equals(album.imageFilePath);

    }

    @Override
    public int hashCode() {
        int result = (int) (albumId ^ (albumId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + year;
        result = 31 * result + imageFilePath.hashCode();
        return result;
    }
}