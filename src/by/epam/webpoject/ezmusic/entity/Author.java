package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Антон on 15.08.2016.
 */
public class Author {
    private Long authorId;
    private String name;
    private String country;
    private String photoPath;

    public Author(){}

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
