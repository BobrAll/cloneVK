package bobr.cloneVK.chat.chatMessage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue
    private int id;
    private Integer chatRoomId;
    private Integer senderId;
    private Integer recipientId;
    private String content;
}
