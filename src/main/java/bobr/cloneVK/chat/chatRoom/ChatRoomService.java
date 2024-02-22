package bobr.cloneVK.chat.chatRoom;

import bobr.cloneVK.chat.chatRoom.model.ChatRoom;
import bobr.cloneVK.chat.chatRoom.model.PrivateChatRoom;
import bobr.cloneVK.chat.chatRoom.model.PublicChatRoom;
import bobr.cloneVK.user.User;
import bobr.cloneVK.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public int getPrivateChatRoomId(int senderId, int recipientId) {
        Set<User> users = new HashSet<>();
        users.add(userRepository.findById(senderId).orElseThrow());
        users.add(userRepository.findById(recipientId).orElseThrow());

        Optional<PrivateChatRoom> chatRoom = chatRoomRepository
                .findPrivateChatRoomByUsers(users);

        return chatRoom.isPresent() ? chatRoom.get().getId() : createPrivateChatRoom(users).getId();
    }

    private ChatRoom createPrivateChatRoom(Set<User> users) {
        PrivateChatRoom chatRoom = PrivateChatRoom.builder()
                .users(users)
                .build();

        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoomDto createPublicChatRoom(CreatePublicChatRequest chatRequest) {
        PublicChatRoom chatRoom = PublicChatRoom.builder()
                .ownerId(chatRequest.getOwnerId())
                .name(chatRequest.getName())
                .users(new HashSet<>(userRepository
                        .findAllById(chatRequest.getUsers())))
                .build();

        chatRoom = chatRoomRepository.save(chatRoom);

        return new ChatRoomDto(chatRequest.getOwnerId(), chatRoom);
    }

    public boolean haveUser(Integer chatId, String login) {
        return !chatRoomRepository.findChatRoomByIdAndUserLogin(chatId, login).isEmpty();
    }

    public Set<ChatRoomDto> getUserChatsByUserId(Integer userId) {
        return chatRoomRepository.findChatRoomsByUserId(userId)
                .stream()
                .map(chatRoom -> new ChatRoomDto(userId, chatRoom))
                .collect(Collectors.toSet());
    }
}
