package application.domain.service.admin;

import application.domain.dto.Book;
import application.port.in.admin.UpdateBookUseCase;
import application.port.out.BookRepositoryPort;
import common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class UpdateBookService implements UpdateBookUseCase {

    private final BookRepositoryPort bookRepositoryPort;

    @Override
    public void update(UUID id, UpdateBookCommand command) {
        log.trace("Updating book with specified id: {} and command: {}", id, command);

        Book book = bookRepositoryPort.readById(id)
                .orElseThrow(
                        () -> new NotFoundException("Book not found with id: " + id)
                );

        book.setAuthor(command.getAuthor());
        book.setPrice(command.getPrice());
        book.setTitle(command.getTitle());
        book.setGenre(command.getGenre());
        book.setPublishedAt(command.getPublishedAt());

        bookRepositoryPort.save(book);
    }
}
