package bobr.cloneVK.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
