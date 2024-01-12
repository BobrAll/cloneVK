package bobr.cloneVK.exceptions.registration;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
        super("User already exist");
    }
}
