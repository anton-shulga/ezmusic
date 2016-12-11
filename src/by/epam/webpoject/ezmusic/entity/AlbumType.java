package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class AlbumType {
    private Long albumTypeId;
    private String name;

    public AlbumType(){

    }

    public Long getAlbumTypeId() {
        return albumTypeId;
    }

    public void setAlbumTypeId(Long albumTypeId) {
        this.albumTypeId = albumTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumType)) return false;

        AlbumType albumType = (AlbumType) o;

        if (!albumTypeId.equals(albumType.albumTypeId)) return false;
        return name.equals(albumType.name);

    }

    @Override
    public int hashCode() {
        int result = albumTypeId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
