package socialmedia;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class Account {

    private int accountID;  // Sequential ID of the account
    private static int nextID;  // Incrementer for accountID
    private String handle;  // "Username" of the account
    private String description;  // "Bio" of the account
    private List<Post> posts;  // All the posts (including comments and endorsements) associated with this Account

    // Constructors

    public Account(String handle)
            throws InvalidHandleException {
        this(handle, null);
    }

    public Account(String handle, String description)
            throws InvalidHandleException{

        verifyHandle(handle);

        this.accountID = nextID++;  // Set the account ID and then increment it
        this.handle = handle;
        this.description = description;
    }

    // Getters/Setters

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle)  throws InvalidHandleException{
        verifyHandle(handle);
        this.handle = handle;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Method

    // Verify if the handle is legal
    private void verifyHandle(@NotNull String handle) throws InvalidHandleException {
        // Check if the handle  is valid
        int handleLength = handle.length();
        if (handleLength < 1 || 30 < handleLength){
            throw new InvalidHandleException("Handle must be between 1 and 30 characters");
        }
        if (handle.contains(" ")){
            throw new InvalidHandleException("Handle must not contain any spaces");
        }
    }

    public int getPostCount(){
        return posts.size();
    }

    public int getEndorsementCount() {
        return (int) posts
                .stream()
                .filter(p -> p instanceof Endorsement)
                .count();
    }
}
