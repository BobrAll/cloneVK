package bobr.cloneVK.chat;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repository;

    public void save(ChatMessage chatMessage) {
        repository.save(chatMessage);
    }
}
