package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private static int nextID = 0;
    private int postID;  // Sequential ID of the post
    private Account account;  // Account associated with this post
    private String message;  // The text content of the post
    private boolean deleted = false;

    private List<Endorsement> endorsements = new ArrayList<>();  // List of endorsements of this post
    private List<Comment> comments = new ArrayList<>();  // List of comments on this post; doesn't count comments on comments

    public Post(Account account, String message) {
        int messageLength = message.length();
        generatePostId();
        this.account = account;
        this.message = message;
    }

    protected void generatePostId(){
        this.postID = nextID++;
    }

    public int getPostID() {
        return postID;
    }

    public Account getAccount() {
        return account;
    }

    public String getMessage() {
        return message;
    }

    public List<Endorsement> getEndorsements() {
        return endorsements;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        this.message = "The original content was removed from the system and is no longer available.";
        this.account = null;
        this.deleted = true;
    }

    public void addEndorsement(Endorsement endorsement) {
        this.endorsements.add(endorsement);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
