package application.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class FlatUser {

    private final UUID id;

    private final String username;

    private final String password;

    @Setter
    private String firstName;

    @Setter
    private String lastName;

    @Setter
    private String email;

    protected BigDecimal accountBalance;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;
}
