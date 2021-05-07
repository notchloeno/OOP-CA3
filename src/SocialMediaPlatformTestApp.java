import socialmedia.*;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

    /**
     * Test method.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println("The system compiled and started the execution...");

        SocialMediaPlatform platform = new SocialMedia();

        try {
            platform.createAccount("bosh", "heehoo");
            platform.createAccount("rachel", "i am helping!");
            int postID = platform.createPost("bosh", "peepeepoopoo");
            int commentID = platform.commentPost("bosh", postID, "ooo very nice heehehehehehe");
            platform.commentPost("bosh", commentID, "first tier");
            platform.commentPost("rachel", 2, "hello!!!!");
            platform.commentPost("bosh", commentID, "second tier");
            int endorsementID = platform.endorsePost("bosh", 3);
            System.out.println(platform.showPostChildrenDetails(0));
        } catch (IllegalHandleException e) {
            e.printStackTrace();
        } catch (InvalidHandleException e) {
            e.printStackTrace();
        } catch (HandleNotRecognisedException e) {
            e.printStackTrace();
        } catch (InvalidPostException e) {
            e.printStackTrace();
        } catch (PostIDNotRecognisedException e) {
            e.printStackTrace();
        } catch (NotActionablePostException e) {
            e.printStackTrace();
        }


        assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
        assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
        assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
        assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

        Integer id;
        try {
            id = platform.createAccount("my_handle");
            assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

            platform.removeAccount(id);
            assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

        } catch (IllegalHandleException e) {
            assert (false) : "IllegalHandleException thrown incorrectly";
        } catch (InvalidHandleException e) {
            assert (false) : "InvalidHandleException thrown incorrectly";
        } catch (AccountIDNotRecognisedException e) {
            assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
        }

    }

}
