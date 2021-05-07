package socialmedia;

/**
 * The type Comment.
 */
public class Comment extends Post {
    private Post referencePost;

    /**
     * Instantiates a new Comment.
     *
     * @param account       the account
     * @param message       the message
     * @param referencePost the reference post
     */
    public Comment(Account account, String message, Post referencePost) {
        super(account, message);
        this.referencePost = referencePost;
    }

    /**
     * Gets reference post.
     *
     * @return the reference post
     */
    public Post getReferencePost() {
        return referencePost;
    }
}
