package application.port.in.superadmin;

import application.domain.dto.User;
import common.validation.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AddAdminUseCase {

    User add(@Valid AddAdminCommand command);

    @Data
    class AddAdminCommand{

        @NotEmpty
        private final String username;
        @NotEmpty
        private final String password;
        @NotEmpty
        private final String firstName;
        @NotEmpty
        private final String lastName;
        @NotEmpty
        @ValidEmail
        private final String email;
    }
}
