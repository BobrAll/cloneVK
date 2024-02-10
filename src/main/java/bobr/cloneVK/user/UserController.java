package bobr.cloneVK.user;

import bobr.cloneVK.exceptions.user.UserNotFoundException;
import bobr.cloneVK.exceptions.user.WrongUserException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @GetMapping()
    Set<UserDto> findUsersByCriteria(@RequestBody UserSearchCriteria criteria) {
        return userService.findByCriteria(criteria)
                .stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toSet());
    }

    @PostMapping("/{userId}/friends/{friendId}")
    void addFriend(@PathVariable Integer userId,
                   @PathVariable Integer friendId) {

        User user = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Пользователь с id=%d н найден.", userId))
                );

        User friend = userService.findById(friendId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Невозможно добавить в друзья " +
                                "пользователя с id=%d, т.к. он не существует.", userId))
                );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getName().equals(user.getUsername()))
            throw new WrongUserException();

        user.addFriend(friend);
        userService.save(user);
    }
}
