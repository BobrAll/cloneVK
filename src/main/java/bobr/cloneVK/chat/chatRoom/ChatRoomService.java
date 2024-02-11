package bobr.cloneVK.chat.chatRoom;

import bobr.cloneVK.user.User;
import bobr.cloneVK.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public int getPrivateChatRoomId(int senderId, int recipientId) {
        Set<User> users = new HashSet<>();
        users.add(userRepository.findById(senderId).orElseThrow());
        users.add(userRepository.findById(recipientId).orElseThrow());

        Optional<ChatRoom> chatRoom = chatRoomRepository
                .findPrivateChatRoomByUsers(users);

        return chatRoom.isPresent() ? chatRoom.get().getId() : createPrivateChatRoom(users).getId();
    }

    private ChatRoom createPrivateChatRoom(Set<User> users) {
        ChatRoom chatRoom = ChatRoom.builder()
                .users(users)
                .build();

        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom createPublicChatRoom(CreatePublicChatRequest chatRequest) {
        ChatRoom chatRoom = ChatRoom.builder()
                .owner(chatRequest.getOwner())
                .name(chatRequest.getName())
                .users(new HashSet<>(userRepository
                        .findAllById(chatRequest.getUsers())))
                .build();

        return chatRoomRepository.save(chatRoom);
    }

    public List<Integer> getChatRoomIdsByUserLogin(String login) {
        return chatRoomRepository.findChatRoomIdsByUserLogin(login);
    }

    public boolean haveUser(Integer chatId, String login) {
        return chatRoomRepository.findChatRoomByIdAndUserLogin(chatId, login).size() > 0;
    }
}
