package application.port.out;

import application.domain.dto.Book;
import application.domain.dto.BookSearchCriteria;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepositoryPort {

    List<Book> readAll();

    Optional<Book> readById(UUID id);

    UUID save(Book book);

    void deleteById(UUID id);

    boolean existsById(UUID id);

    List<Book> searchBooks(BookSearchCriteria criteria);
}
