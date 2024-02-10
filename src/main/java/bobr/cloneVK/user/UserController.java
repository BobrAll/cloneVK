package bobr.cloneVK.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User")
@CrossOrigin(allowCredentials = "true")
public class UserController {

    private final UserService userService;

    @GetMapping()
    Set<UserDto> findUsersByCriteria(@RequestBody UserSearchCriteria criteria) {
        return userService.findByCriteria(criteria)
                .stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toSet());
    }

    @GetMapping("/{userId}/friends")
    Set<Integer> getUserFriends(@PathVariable Integer userId) {
        return userService.getFriends(userId);
    }

    @PostMapping("/{userId}/friends/{friendId}")
    void addFriend(@PathVariable Integer userId,
                   @PathVariable Integer friendId) {

        User user = userService.findById(userId);
        User friend = userService.findById(friendId);

        userService.checkAccess(user);

        user.addFriend(friend);
        userService.save(user);
    }
}
