package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class Genre {
    private Long genreId;
    private String name;

    public Genre() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;

        Genre genre = (Genre) o;

        if (!genreId.equals(genre.genreId)) return false;
        return name.equals(genre.name);

    }

    @Override
    public int hashCode() {
        int result = genreId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
