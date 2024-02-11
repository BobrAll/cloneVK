package bobr.cloneVK.chat.chatRoom;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePublicChatRequest {
    @NotNull
    private Integer owner;
    private Set<Integer> users;
    @Size(min = 1, max = 16)
    private String name;
}
