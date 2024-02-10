package bobr.cloneVK.exceptions.user;

import bobr.cloneVK.exceptions.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleNoSuchFlatException(UserNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(new ExceptionBody(e.getMessage(), status), status);
    }

    @ExceptionHandler(value = {WrongUserException.class})
    public ResponseEntity<Object> handleNoSuchFlatException(WrongUserException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        return new ResponseEntity<>(new ExceptionBody(e.getMessage(), status), status);
    }
}
