package socialmedia;

import java.util.List;

public class Post {
    private static int nextID = 0;
    private int postID;  // Sequential ID of the post
    private Account account;  // Account associated with this post
    private String message;  // The text content of the post

    private List<Endorsement> endorsements;  // List of endorsements of this post
    private List<Comment> comments;  // List of comments on this post; doesn't count comments on comments

    public Post(Account account, String message) throws InvalidPostException {
        int messageLength = message.length();
        if (1 > messageLength || messageLength > 100){
            throw new InvalidPostException("Message must be between 1-100 characters");
        }
        this.postID = nextID++;
        this.account = account;
        this.message = message;
    }

    public int getPostID() {
        return postID;
    }
}
