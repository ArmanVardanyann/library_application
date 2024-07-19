package application.port.in.admin;

import application.domain.dto.User;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface ReadUserUseCase {

    User readById(UUID id);

    List<User> readAll();
}
