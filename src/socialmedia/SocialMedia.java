package socialmedia;

import java.io.IOException;
import java.util.List;

/**
 * SocialMedia is the functioning implementor of
 * the SocialMediaPlatform interface.
 * 
 * @author Josh Roberts
 * @author Ellie Vallard
 * @version 1.0
 */
public class SocialMedia implements SocialMediaPlatform {

	private List<Account> accountList;
	private List<Post> postList;

	public Account getAccount(String handle){
		for (Account account : accountList){
			if (account.getHandle().equals(handle)){
				return account;
			}
		}
		return null;
	}

	// Originally this was going to throw HandleNotRecognisedException for normalisation's sake, but we decided
	// that the inflexibility with error messages would make it not worth it. Another possibility would be to
	// have an optional error message parsed in, but this would require refactoring, and strings being passed
	// around for, likely, often no reason as exceptions should not be happening very often.
	// Basically, we accept that there will be repeated code so that in the rare case an exception is thrown
	// a more descriptive error message will be given.
	public Account getAccount(int accountID) {
		for (Account account : accountList){
			if (account.getAccountID() == accountID){
				return account;
			}
		}
		return null;
	}

	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        createAccount(handle, null);
        return 0;
	}

	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		if (getAccount(handle) != null){
			throw new IllegalHandleException("This handle is already associated with another account");
		}
		Account newAccount = new Account(handle);
		accountList.add(newAccount);
		return 0;
	}

	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		Account account = getAccount(id);
		if (account == null) { throw new AccountIDNotRecognisedException();}
		removeAccount(account);
	}

	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		Account account = getAccount(handle);
		if (account == null) { throw new HandleNotRecognisedException();}
		removeAccount(account);
	}

	public void removeAccount(Account account){

	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
	    Account account = getAccount(oldHandle);
	    if (account == null) {
	    	throw new HandleNotRecognisedException("Account with old handle cannot be found"); }
	    if (getAccount(newHandle) != null) {
	    	throw new IllegalHandleException("New handle is already associated with another account"); }
	    account.setHandle(newHandle);
	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		Account account = getAccount(handle);
		if (account == null) {
			throw new HandleNotRecognisedException("Account with handle cannot be found");
		}
		account.setDescription(description);
	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
	    Account account = getAccount(handle);
	    if (account == null){
	    	throw new HandleNotRecognisedException("Account with handle cannot be found");
		}
	    String outputString = "";
		outputString += "ID:" + String.valueOf(account.getAccountID());
	    outputString += "Handle: " + handle;
	    String description = account.getDescription();
	    if (description != null) {
	    	outputString += "Description:" + account.getDescription();
		}
	    outputString += "Post count:" + String.valueOf(account.getPostCount());
	    outputString += "Endorse count:" + String.valueOf(account.getEndorsementCount());
		return outputString;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
	    Account account = getAccount(handle);
	    if (account == null){
	    	throw new HandleNotRecognisedException("Account with handle cannot be found");
		}
	    Post post = new Post(account, message);
	    return post.getPostID();
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {

		return 0;
	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfAccounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCommentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedPost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void erasePlatform() {
		// TODO Auto-generated method stub

	}

	@Override
	public void savePlatform(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
