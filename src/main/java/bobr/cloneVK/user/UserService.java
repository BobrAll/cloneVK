package bobr.cloneVK.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Optional<User> loadByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<User> loadById(Integer id) {
        return repository.findById(id);
    }

    public void save(User user) {
        repository.save(user);
    }
}
