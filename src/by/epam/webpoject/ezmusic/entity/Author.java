package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Антон on 15.08.2016.
 */
public class Author {
    private Long authorId;
    private String name;
    private String country;
    private String photoPath;
    private AuthorType authorType;
    private Label label;

    public Author() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (!authorId.equals(author.authorId)) return false;
        if (!name.equals(author.name)) return false;
        if (!country.equals(author.country)) return false;
        return photoPath.equals(author.photoPath);

    }

    @Override
    public int hashCode() {
        int result = authorId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + photoPath.hashCode();
        return result;
    }

    public AuthorType getAuthorType() {
        return authorType;
    }

    public void setAuthorType(AuthorType authorType) {
        this.authorType = authorType;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
