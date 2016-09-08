package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Антон on 28.07.2016.
 */
public class Comment {
    private long commentId;
    private long songId;
    private User user;
    private int rating;
    private String text;

    public Comment(){}

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (songId != comment.songId) return false;
        if (rating != comment.rating) return false;
        if (!user.equals(comment.user)) return false;
        return text.equals(comment.text);

    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (int) (songId ^ (songId >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + rating;
        result = 31 * result + text.hashCode();
        return result;
    }
}
