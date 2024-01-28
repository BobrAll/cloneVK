package bobr.cloneVK.chat;

import bobr.cloneVK.chatRoom.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class RestChatController {
    private final ChatRoomRepository repository;

    @GetMapping("/user/{userId}")
    public List<Integer> getChatsList(@PathVariable Integer userId) {
        return repository.findChatRoomIdsByUserId(userId);
    }
}
