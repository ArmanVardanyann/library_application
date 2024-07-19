package application.port.in.admin;

import application.domain.dto.Book;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface ReadBookUseCase {

    List<Book> readAll();

    Book readById(UUID id);
}
