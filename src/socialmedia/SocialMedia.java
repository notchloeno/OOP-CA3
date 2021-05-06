package socialmedia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SocialMedia is the functioning implementor of
 * the SocialMediaPlatform interface.
 * 
 * @author Josh Roberts
 * @author Ellie Vallard
 * @version 1.0
 */
public class SocialMedia implements SocialMediaPlatform {

	private List<Account> accountList = new ArrayList<>();
	private List<Post> postList = new ArrayList<>();

	public Account getAccount(String handle) {
		for (Account account : accountList) {
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
		for (Account account : accountList) {
			if (account.getAccountID() == accountID){
				return account;
			}
		}
		return null;
	}

	public Post getPost(int postID) throws PostIDNotRecognisedException {
		for (Post post : postList) {
			if (post.getPostID() == postID) {
				return post;
			}
		}
		throw new PostIDNotRecognisedException("Could not find post with ID");
	}

	private void checkPostValidity(String message) throws InvalidPostException {
		int messageLength = message.length();
		if (1 > messageLength || messageLength > 100){
			throw new InvalidPostException("Message must be between 1-100 characters");
		}
	}

	/*
	private List<Comment> getPostComments(Post post) {
	    return postList
				.stream()
				.filter(p -> p.getClass().equals(Comment.class))
				.map(c -> (Comment) c)  // This is required so the compiler is happy the list contains only Comments
				.collect(Collectors.toList());
	}

	private int getPostCommentCount(Post post) {
		return (int) postList
				.stream()
				.filter(p -> (p instanceof Comment && !p.isDeleted()))
				.count();
	}

	private int getPostEndorsementCount(Post post) {
		return (int) postList
				.stream()
				.filter(p -> (p instanceof Endorsement && !p.isDeleted()))
				.count();
	}
	*/

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
		checkPostValidity(message);
		Post post = new Post(account, message);
		postList.add(post);
	    return post.getPostID();
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		Account account = getAccount(handle);
		if (account == null) {
			throw new HandleNotRecognisedException("Account with handle cannot be found");
		}

		Post post = getPost(id);
		if (post instanceof Endorsement) {
			throw new NotActionablePostException("Cannot endorse an Endorsement");
		}
		if (post.isDeleted()) {
			throw new NotActionablePostException("Cannot endorse a deleted post");
		}
		// Generate the message for the endorsement
		// Instantiate the Endorsement object
		Endorsement endorsement = new Endorsement(account, post);
		post.addEndorsement(endorsement);
		postList.add(endorsement);
		return endorsement.getPostID();
	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		Account account = getAccount(handle);
		if (account == null) {
			throw new HandleNotRecognisedException("Account with handle cannot be found");
		}

		Post post = getPost(id);
		if (post.isDeleted()) {
			throw new NotActionablePostException("Cannot comment on a deleted post");
		}
		checkPostValidity(message);

		Comment comment = new Comment(account, message, post);
		post.addComment(comment);
		postList.add(comment);
		return comment.getPostID();
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
	    getPost(id).delete();  // The exception is thrown in getPost so we can one-liner this one boys
	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		return showIndividualPost(id, 0);
	}

	public String showIndividualPost(int id, int depth) throws PostIDNotRecognisedException {
		Post post = getPost(id);
		String indent = "\t".repeat(depth);
		String buffer = depth > 0 ? "\t".repeat(depth - 1) + "| > " : "";
		String string = String.format("%sID: %s\n%sAccount: %s\n%sNo. Endorsements: %s | No. Comments: %s\n%s%s",
				buffer, String.valueOf(post.getPostID()),
				indent, post.getAccount().getHandle(),
				// indent, String.valueOf(getPostEndorsementCount(post)),
				//String.valueOf(getPostCommentCount(post)),
				indent, post.getEndorsements().size(),
				post.getComments().size(),
				indent, post.getMessage());
		return string;
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		return showPostChildrenDetails(id, 0);
	}

	public StringBuilder showPostChildrenDetails(int id, int depth)
			throws PostIDNotRecognisedException, NotActionablePostException {
		Post post = getPost(id);
		if (post instanceof Endorsement) {
			throw new NotActionablePostException("Endorsements have no child posts, use showIndividualPost instead");
		}
	    StringBuilder details = new StringBuilder();
	    // Add the parent post to the StringBuilder
		details.append(showIndividualPost(id, depth));
		// For every comment, append post details plus indentation
		// You'll be wanting to call this method recursively
		List<Comment> commentList = post.getComments();
		if (commentList.size() == 0) {
			return details;
		}
		for (Comment comment : commentList) {
			String indent = "\t".repeat(depth);
			String buffer = String.format("\n%s|\n%s|\n", indent, indent);
			details.append(buffer);
			details.append(showPostChildrenDetails(comment.getPostID(), depth + 1));
		}
		return details;
	}

	@Override
	public int getNumberOfAccounts() {
	    return (int) accountList
				.stream()
				.filter(a -> !a.isDeleted())
				.count();
	}

	@Override
	public int getTotalOriginalPosts() {
	    return (int) postList
				.stream()
				.filter(p -> !(p instanceof Endorsement || p instanceof Comment))
				.map(p -> (Post) p)
				.filter(p -> !p.isDeleted())
				.count();
	}

	@Override
	public int getTotalEndorsmentPosts() {
	    return (int) postList
				.stream()
				.filter(p -> p instanceof Endorsement)
				.map(e -> (Endorsement) e)
				.filter(e -> !e.isDeleted())
				.count();
	}

	@Override
	public int getTotalCommentPosts() {
	    return (int) postList
				.stream()
				.filter(p -> p instanceof Comment)
				.map(c -> (Comment) c)
				.filter(c -> !c.isDeleted())
				.count();
	}

	@Override
	public int getMostEndorsedPost() {
		// If this method is called before any posts are created it will throw a stack trace. This is because it
		// is not possible to return null for generic types such as int, and we cannot change it to Integer because
		// otherwise it may break the scripts that test our programs RIP. Therefore it will simply cry at you if you
		// try to break it, and then return 0;
		Post topPost;
		int mostEndorsements;
		// Attempt to get a starting value for "best post"
		try {
			topPost = getPost(0);
			mostEndorsements = topPost.getEndorsements().size();
		} catch (PostIDNotRecognisedException e) {
			e.printStackTrace();
			return 0;
		}
		// Check each post to see if it has more endorsements. This counts
		// comments because the specification doesn't say otherwise
		for (Post post : postList) {
			int endorsementCount = post.getEndorsements().size();
			if (endorsementCount > mostEndorsements) {
				topPost = post;
			}
		}
		return topPost.getPostID();
	}

	@Override
	public int getMostEndorsedAccount() {
		Account topAccount;
		int mostEndorsements;
		// Attempt to get a starting value for "best post"
		topAccount = getAccount(0);
		// If there are no accounts, cry and return 0
		if (topAccount == null) {
			System.out.println("Tried to get most endorsed account when no accounts exist. This may cause issues");
			return 0;
		}
		mostEndorsements = topAccount.getEndorsementCount();
		// Check each post to see if it has more endorsements. This counts
		// comments because the specification doesn't say otherwise
		for (Account account : accountList) {
			int endorsementCount = account.getEndorsementCount();
			if (endorsementCount > mostEndorsements) {
				topAccount = account;
			}
		}
		return topAccount.getAccountID();
	}

	@Override
	public void erasePlatform() {
		// Set references to objects to null so they are deleted by the garbage collector
		for (Account account : accountList) {
			account = null;
		}
		for (Post post : postList) {
			post = null;
		}
		// Create new empty lists (which inherently sets references to the old lists to null)
		accountList = new ArrayList<>();
		postList = new ArrayList<>();
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
