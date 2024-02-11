package bobr.cloneVK.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE (:login is null or :login = u.login) " +
            "AND (:firstname is null or :firstname = u.firstname) " +
            "AND (:lastname is null or :lastname = u.lastname) " +
            "ORDER BY u.lastname " +
            "LIMIT 50"
    )
    Set<User> findByCriteria(String login,
                             String firstname,
                             String lastname);

    @Query("SELECT u.friends FROM User u " +
            "WHERE u.id = :userId")
    Set<User> getFriends(Integer userId);

    Optional<User> findByEmail(String email);

    Optional<User> findUserByLogin(String login);
}
