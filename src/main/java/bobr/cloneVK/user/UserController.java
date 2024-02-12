package bobr.cloneVK.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @GetMapping()
    @Operation(summary = "Find users by criteria")
    Set<UserDto> findUsersByCriteria(@RequestBody UserSearchCriteria criteria) {
        return userService.convertToUserDto(userService.findByCriteria(criteria));
    }

    @GetMapping("/{userId}/friends")
    @Operation(summary = "Get list of user friends")
    Set<UserDto> getUserFriends(@PathVariable Integer userId) {
        return userService.getFriends(userId);
    }

    @PostMapping("/{userId}/friends/{friendId}")
    @Operation(summary = "Add new friend")
    void addFriend(@PathVariable Integer userId,
                   @PathVariable Integer friendId) {

        User user = userService.findById(userId);
        User friend = userService.findById(friendId);

        userService.checkAccess(user);

        user.addFriend(friend);
        userService.save(user);
    }
}
