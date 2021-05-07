package socialmedia;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Post.
 */
public class Post {
    private static int nextID = 0;
    private int postID;  // Sequential ID of the post
    private Account account;  // Account associated with this post
    private String message;  // The text content of the post
    private boolean deleted = false;

    private List<Endorsement> endorsements = new ArrayList<>();  // List of endorsements of this post
    private List<Comment> comments = new ArrayList<>();  // List of comments on this post; doesn't count comments on comments

    /**
     * Instantiates a new Post.
     *
     * @param account the account
     * @param message the message
     */
    public Post(Account account, String message) {
        generatePostId();
        this.account = account;
        this.message = message;
    }

    /**
     * Generate post id.
     */
    protected void generatePostId() {
        this.postID = nextID++;
    }

    /**
     * Gets post id.
     *
     * @return the post id
     */
    public int getPostID() {
        return postID;
    }

    /**
     * Gets account.
     *
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets endorsements.
     *
     * @return the endorsements
     */
    public List<Endorsement> getEndorsements() {
        return endorsements;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Is deleted boolean.
     *
     * @return the boolean
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Delete.
     */
    public void delete() {
        this.message = "The original content was removed from the system and is no longer available.";
        this.account = null;
        this.deleted = true;
    }

    /**
     * Add endorsement.
     *
     * @param endorsement the endorsement
     */
    public void addEndorsement(Endorsement endorsement) {
        this.endorsements.add(endorsement);
    }

    /**
     * Add comment.
     *
     * @param comment the comment
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
