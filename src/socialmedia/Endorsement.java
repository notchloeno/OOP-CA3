package socialmedia;

/**
 * The type Endorsement.
 */
public class Endorsement extends Post {
    private Post endorsedPost;

    /**
     * Instantiates a new Endorsement.
     *
     * @param account      the account
     * @param endorsedPost the endorsed post
     */
    public Endorsement(Account account, Post endorsedPost) {
        super(account, generateEndorsementMessage(endorsedPost));
        this.endorsedPost = endorsedPost;
        generatePostId();
    }

    private static String generateEndorsementMessage(Post endorsedPost) {
        return String.format("EP@%s: %s",
                endorsedPost.getAccount().getHandle(),
                endorsedPost.getMessage()
        );
    }

}
