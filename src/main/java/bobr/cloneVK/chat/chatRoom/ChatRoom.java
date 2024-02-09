package bobr.cloneVK.chat.chatRoom;

import bobr.cloneVK.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue
    private Integer id;
    private boolean isGroup;
    @ManyToMany
    @JoinTable(name = "users_in_chat")
    private Set<User> users = new HashSet<>();
}
