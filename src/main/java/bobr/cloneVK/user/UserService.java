package bobr.cloneVK.user;

import bobr.cloneVK.exceptions.user.UserNotFoundException;
import bobr.cloneVK.exceptions.user.WrongUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<User> findByLogin(String login) {
        return repository.findUserByLogin(login);
    }

    public User findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Пользователь с id=%d н найден.", id))
                );
    }

    public void save(User user) {
        repository.save(user);
    }

    public Set<User> findByCriteria(UserSearchCriteria criteria) {
        return repository.findByCriteria(
                criteria.getLogin(),
                criteria.getFirstname(),
                criteria.getLastname());
    }

    public void checkAccess(Integer userId) {
        User user = findById(userId);

        checkAccess(user);
    }

    public void checkAccess(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals(user.getUsername()))
            throw new WrongUserException();
    }

    public Set<Integer> getFriends(Integer userId) {
        return repository.getFriends(userId);
    }
}
