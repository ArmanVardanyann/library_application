package application.port.in.admin;

import common.validation.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface UpdateUserUseCase {

    void update(UUID uuid, @Valid UpdateUserCommand updateUserCommand);

    @Data
    class UpdateUserCommand {

        @NotEmpty
        private final String firstName;

        @NotEmpty
        private final String lastName;

        @NotEmpty
        @ValidEmail
        private final String email;
    }
}
