package bobr.cloneVK.chat.chatMessage;

import bobr.cloneVK.chat.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message")
    public void sendMessage(ChatMessage chatMessage) {
        if (chatMessage.getChatRoomId() == null) {
            Integer chatRoomId =
                    chatRoomService.getPrivateChatRoomId(
                            chatMessage.getSenderId(),
                            chatMessage.getRecipientId()
                    );

            chatMessage.setChatRoomId(chatRoomId);
        }

        chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId().toString(),
                "/queue/messages",
                chatMessage
        );
    }
}
