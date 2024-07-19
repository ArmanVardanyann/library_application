package application.port.in.admin;

import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface DeleteBookUseCase {

    void deleteById(UUID id);
}
