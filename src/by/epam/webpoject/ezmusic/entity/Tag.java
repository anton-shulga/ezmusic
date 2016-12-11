package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class Tag {
    private Long tagId;
    private String name;


    public Tag(){

    }
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
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
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (!tagId.equals(tag.tagId)) return false;
        return name.equals(tag.name);

    }

    @Override
    public int hashCode() {
        int result = tagId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
