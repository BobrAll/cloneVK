package bobr.cloneVK.registration;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
        super("User already exist");
    }
}
