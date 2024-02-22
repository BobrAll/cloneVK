package bobr.cloneVK.chat.chatRoom;

import bobr.cloneVK.chat.chatRoom.model.ChatRoom;
import bobr.cloneVK.chat.chatRoom.model.PrivateChatRoom;
import bobr.cloneVK.chat.chatRoom.model.PublicChatRoom;
import bobr.cloneVK.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatRoomDto {
    private Integer id;
    private Integer ownerId;
    private String name;
    private Set<UserDto> users;

    public ChatRoomDto(Integer requesterId, ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.users = chatRoom.getUsers()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toSet());

        if (chatRoom instanceof PublicChatRoom publicChatRoom) {

            this.ownerId = publicChatRoom.getOwnerId();
            this.name = publicChatRoom.getName();

        } else if (chatRoom instanceof PrivateChatRoom privateChatRoom) {

            Optional<String> username = privateChatRoom.getUsers()
                    .stream()
                    .filter(user -> !user.getId().equals(requesterId))
                    .map(user -> user.getFirstname() + " " + user.getLastname())
                    .findFirst();

            if (username.isEmpty())
                throw new RuntimeException("ChatRoomDto exception, in chat only 1 user or less");
            else
                this.name = username.get();

        }
    }
}
