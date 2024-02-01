package bobr.cloneVK.exceptions.chat;

public class UserDoesNotHaveAccessToChatException extends RuntimeException {
    public UserDoesNotHaveAccessToChatException() {
        super("You haven't access to this chat.");
    }
}
