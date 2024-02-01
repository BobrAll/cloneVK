package bobr.cloneVK.chat;

import bobr.cloneVK.chatRoom.ChatRoomService;
import bobr.cloneVK.exceptions.chat.UserDoesNotHaveAccessToChatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findMessages(String login, Integer chatId) {
        if (!chatRoomService.haveUser(chatId, login))
            throw new UserDoesNotHaveAccessToChatException();

        return chatMessageRepository.findAllByChatRoomId(chatId);
    }
}
