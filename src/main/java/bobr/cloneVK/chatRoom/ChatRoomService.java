package bobr.cloneVK.chatRoom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository repository;

    public String getChatRoomId(String senderId, String recipientId) {
        return repository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatRoomId)
                .orElse(createChatRoom(senderId, recipientId));
    }

    private String createChatRoom(String senderId, String recipientId) {
        String chatRoomId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatRoomId(chatRoomId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        repository.save(senderRecipient);
        repository.save(recipientSender);

        return chatRoomId;
    }
}
