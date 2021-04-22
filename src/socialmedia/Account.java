package socialmedia;

import java.util.List;

public class Account {

    private int accountID;  // Sequential ID of the account
    private static int nextID;  // Incrementer for accountID
    private String handle;  // "Username" of the account
    private String description;  // "Bio" of the account
    private List<Post> posts;  // All the posts (including comments and endorsements) associated with this Account

    // Verify if the handle is legal
    private boolean verifyHandle(String handle) throws IllegalHandleException, InvalidHandleException {
        // Check if the handle  is valid
        int handleLength = handle.length();
        if (handleLength < 1 || 30 < handleLength){
            throw new InvalidHandleException("Handle must be between 1 and 30 characters");
        }
        // Check the handle hasn't been used already
        return true;
    }

    private boolean verifyDescription(String description){
        return true;
    }

    public Account(String handle, String description)
            throws IllegalHandleException, InvalidHandleException{

        try
        {
            verifyHandle(handle);
            verifyDescription(description);
        }
        catch (IllegalHandleException e)
        {
            throw e;
        }
        catch (InvalidHandleException e){
            throw e;
        }

        this.accountID = nextID++;  // Set the account ID and then increment it
        this.handle = handle;
        this.description = description;
    }
}
