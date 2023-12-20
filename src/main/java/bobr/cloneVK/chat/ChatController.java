package bobr.cloneVK.chat;

import bobr.cloneVK.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message")
    public void sendMessage(ChatMessage chatMessage) {
        String chatRoomId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId()
        );

        chatMessage.setChatRoomId(chatRoomId);
        chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),
                "/queue/messages",
                chatMessage
        );
    }
}
