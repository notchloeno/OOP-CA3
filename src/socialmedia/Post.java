package socialmedia;

import java.util.List;

public class Post {
    private int postID;  // Sequential ID of the post
    private Account account;  // Account associated with this post
    private String message;  // The text content of the post

    private List<Endorsement> endorsements;  // List of endorsements of this post
    private List<Comment> comments;  // List of comments on this post; doesn't count comments on comments


}
