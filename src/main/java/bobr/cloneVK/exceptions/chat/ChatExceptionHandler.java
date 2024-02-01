package bobr.cloneVK.exceptions.chat;

import bobr.cloneVK.exceptions.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ChatExceptionHandler {

    @ExceptionHandler(value = {UserDoesNotHaveAccessToChatException.class})
    public ResponseEntity<Object> handleUserDoesNotHaveAccessToChatException(UserDoesNotHaveAccessToChatException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        return new ResponseEntity<>(new ExceptionBody(e.getMessage(), status), status);
    }
}
