package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Антон on 28.07.2016.
 */
public class Comment {
    private long commentId;
    private int rating;
    private String text;

    public Comment(){}

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
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
}
