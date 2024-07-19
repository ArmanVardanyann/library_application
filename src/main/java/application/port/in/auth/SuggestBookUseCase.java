package application.port.in.auth;

import application.domain.dto.Book;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface SuggestBookUseCase {

    List<Book> suggest(UUID userId);
}
