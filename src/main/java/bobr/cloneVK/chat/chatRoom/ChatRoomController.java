package bobr.cloneVK.chat.chatRoom;

import bobr.cloneVK.chat.chatMessage.ChatMessage;
import bobr.cloneVK.chat.chatMessage.ChatMessageService;
import bobr.cloneVK.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/chats")
@RequiredArgsConstructor
@Tag(name = "Chat")
public class ChatRoomController {
    private final UserService userService;
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @GetMapping("/users/{userId}")
    @Operation(summary = "Get user chats")
    public Set<ChatRoomDto> getChatsList(@PathVariable Integer userId) {
        userService.checkAccess(userId);

        return chatRoomService.getUserChatsByUserId(userId);
    }

    @GetMapping("/{chatId}/messages")
    @Operation(summary = "Get messages in chat")
    public List<ChatMessage> getMessages(@PathVariable Integer chatId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        return chatMessageService.findMessages(login, chatId);
    }

    @PostMapping()
    @Operation(summary = "Create new public chat")
    public ChatRoomDto createPublicChat(@RequestBody @Valid CreatePublicChatRequest chatRequest) {
        userService.checkAccess(chatRequest.getOwnerId());

        return chatRoomService.createPublicChatRoom(chatRequest);
    }
}
