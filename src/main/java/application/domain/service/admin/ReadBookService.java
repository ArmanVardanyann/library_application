package application.domain.service.admin;

import application.domain.dto.Book;
import application.port.in.admin.ReadBookUseCase;
import application.port.out.BookRepositoryPort;
import common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadBookService implements ReadBookUseCase {

    private final BookRepositoryPort bookRepositoryPort;

    @Override
    public List<Book> readAll() {
        log.trace("Fetching all the books");

        return bookRepositoryPort.readAll();
    }

    @Override
    public Book readById(UUID id) {
        log.trace("Fetching book with specified id: {}", id);

        return bookRepositoryPort.readById(id)
                .orElseThrow(
                        () -> new NotFoundException("Book is not found with provided id: " + id)
                );
    }
}
