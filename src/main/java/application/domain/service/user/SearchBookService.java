package application.domain.service.user;

import application.domain.dto.Book;
import application.domain.dto.BookSearchCriteria;
import application.port.in.user.SearchBookUseCase;
import application.port.out.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SearchBookService implements SearchBookUseCase {

    private final BookRepositoryPort bookRepositoryPort;

    @Override
    public List<Book> search(BookSearchCriteria criteria) {
        log.trace("Searching books with the given criteria: {}", criteria);

        return bookRepositoryPort.searchBooks(criteria);
    }
}
