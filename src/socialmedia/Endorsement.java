package socialmedia;

public class Endorsement extends Post {
    private Post endorsedPost;

    private static String generateEndorsementMessage(Post endorsedPost) {
        return String.format("EP@%s: %s",
                endorsedPost.getAccount().getHandle(),
                endorsedPost.getMessage()
        );
    }
    public Endorsement(Account account, Post endorsedPost) {
        super(account, generateEndorsementMessage(endorsedPost));
        this.endorsedPost = endorsedPost;
        generatePostId();
    }

}
