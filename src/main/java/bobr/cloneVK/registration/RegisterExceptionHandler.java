package bobr.cloneVK.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RegisterExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyRegisteredException.class})
    public ResponseEntity<Object> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        RegisterExceptionBody registerExceptionBody = new RegisterExceptionBody(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(registerExceptionBody, status);
    }
}
