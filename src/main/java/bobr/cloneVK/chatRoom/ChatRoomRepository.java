package bobr.cloneVK.chatRoom;

import bobr.cloneVK.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    @Query("SELECT c FROM ChatRoom c JOIN c.users u WHERE u IN :users GROUP BY c HAVING COUNT(u) = 2")
    Optional<ChatRoom> findByUsers(Set<User> users);

    @Query("SELECT c.id FROM ChatRoom c JOIN c.users u WHERE u.id = :userId")
    List<Integer> findChatRoomIdsByUserId(Integer userId);

    @Query("SELECT c.id FROM ChatRoom c JOIN c.users u WHERE u.login = :login")
    List<Integer> findChatRoomIdsByUserLogin(String login);

    @Query("SELECT c FROM ChatRoom c JOIN c.users u WHERE u.login = :login AND c.id = :chatId")
    List<ChatRoom> findChatRoomByIdAndUserLogin(Integer chatId, String login);
}
