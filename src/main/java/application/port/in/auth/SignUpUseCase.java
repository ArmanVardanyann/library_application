package application.port.in.auth;

import application.domain.dto.User;
import common.validation.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public interface SignUpUseCase {

    User signUp(@Valid SignUpCommand command);

    @Data
    class SignUpCommand {

        @NotEmpty
        private final String username;

        @NotEmpty
        private final String password;

        @NotEmpty
        private final String firstName;

        @NotEmpty
        private final String lastName;

        @PositiveOrZero
        private final BigDecimal accountBalance;

        @NotEmpty
        @ValidEmail
        private final String email;
    }
}
