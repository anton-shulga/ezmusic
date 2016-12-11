package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class AuthorType {
    private Long authorTypeId;
    private String name;

    public AuthorType(){

    }

    public Long getAuthorTypeId() {
        return authorTypeId;
    }

    public void setAuthorTypeId(Long authorTypeId) {
        this.authorTypeId = authorTypeId;
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
        if (!(o instanceof AuthorType)) return false;

        AuthorType that = (AuthorType) o;

        if (!authorTypeId.equals(that.authorTypeId)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = authorTypeId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
