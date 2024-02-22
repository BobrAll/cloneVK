package bobr.cloneVK.chat.chatRoom.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
//@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PrivateChatRoom extends ChatRoom {
}
