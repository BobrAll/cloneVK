package bobr.cloneVK.chat.chatRoom;

import bobr.cloneVK.chat.chatRoom.model.ChatRoom;
import bobr.cloneVK.chat.chatRoom.model.PrivateChatRoom;
import bobr.cloneVK.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    @Query("SELECT c FROM ChatRoom c JOIN c.users u WHERE u.id = :userId")
    List<ChatRoom> findChatRoomsByUserId(Integer userId);

    @Query("SELECT c FROM PrivateChatRoom c JOIN c.users u WHERE u IN :users")
    Optional<PrivateChatRoom> findPrivateChatRoomByUsers(Set<User> users);

    @Query("SELECT c FROM ChatRoom c JOIN c.users u WHERE u.login = :login AND c.id = :chatId")
    List<ChatRoom> findChatRoomByIdAndUserLogin(Integer chatId, String login);

}
