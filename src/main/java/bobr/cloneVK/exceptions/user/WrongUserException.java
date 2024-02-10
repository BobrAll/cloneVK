package bobr.cloneVK.exceptions.user;

public class WrongUserException extends RuntimeException {
    public WrongUserException() {
        super("Вы не имеете доступа к данному пользователю.");
    }
}
