package application.port.in.auth;

import application.domain.dto.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
public interface SignInUseCase {

    User signIn(@Valid SignInCommand command);

    @Data
    class SignInCommand {

        @NotEmpty
        private final String username;
        @NotEmpty
        private final String password;

    }
}
