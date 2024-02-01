package bobr.cloneVK.jwt;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Jwt {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String token;
}
