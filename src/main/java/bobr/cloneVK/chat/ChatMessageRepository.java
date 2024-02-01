package bobr.cloneVK.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    List<ChatMessage> findAllByChatRoomId(Integer chatRoomId);
}
