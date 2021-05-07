package socialmedia;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Account.
 */
public class Account {

    private static int nextID;  // Incrementer for accountID
    private int accountID;  // Sequential ID of the account
    private String handle;  // "Username" of the account
    private String description;  // "Bio" of the account
    private List<Post> posts = new ArrayList<>();  // All the posts (including comments and endorsements) associated with this Account
    private boolean deleted = false;

    // Constructors

    /**
     * Instantiates a new Account.
     *
     * @param handle the handle
     * @throws InvalidHandleException the invalid handle exception
     */
    public Account(String handle)
            throws InvalidHandleException {
        this(handle, null);
    }

    /**
     * Instantiates a new Account.
     *
     * @param handle      the handle
     * @param description the description
     * @throws InvalidHandleException the invalid handle exception
     */
    public Account(String handle, String description)
            throws InvalidHandleException {

        verifyHandle(handle);

        this.accountID = nextID++;  // Set the account ID and then increment it
        this.handle = handle;
        this.description = description;
    }

    // Getters/Setters

    /**
     * Gets handle.
     *
     * @return the handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Sets handle.
     *
     * @param handle the handle
     * @throws InvalidHandleException the invalid handle exception
     */
    public void setHandle(String handle) throws InvalidHandleException {
        verifyHandle(handle);
        this.handle = handle;
    }

    /**
     * Gets account id.
     *
     * @return the account id
     */
    public int getAccountID() {
        return accountID;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // Method

    // Verify if the handle is legal
    private void verifyHandle(String handle) throws InvalidHandleException {
        // Check if the handle  is valid
        int handleLength = handle.length();
        if (1 > handleLength || handleLength > 101) {
            throw new InvalidHandleException("Handle must be between 1 and 30 characters");
        }
        if (handle.contains(" ")) {
            throw new InvalidHandleException("Handle must not contain any spaces");
        }
    }

    /**
     * Gets post count.
     *
     * @return the post count
     */
    public int getPostCount() {
        return posts.size();
    }

    /**
     * Gets endorsement count.
     *
     * @return the endorsement count
     */
    public int getEndorsementCount() {
        return (int) posts
                .stream()
                .filter(p -> (p instanceof Endorsement && !p.isDeleted()))
                .count();
    }

    /*
    public int getCommentCount() {
        return (int) posts
                .stream()
                .filter(p -> (p instanceof Comment && !p.isDeleted()))
                .count();
    }
    */

    /**
     * Delete.
     */
    public void delete() {
        this.deleted = true;
        for (Post post : posts) {
            post.delete();
        }
    }
}
