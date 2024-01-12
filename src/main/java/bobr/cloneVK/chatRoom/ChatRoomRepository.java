package bobr.cloneVK.chatRoom;

import bobr.cloneVK.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    @Query("SELECT c FROM ChatRoom c JOIN c.users u WHERE u IN :users GROUP BY c HAVING COUNT(u) = 2")
    Optional<ChatRoom> findByUsers(Set<User> users);
}
