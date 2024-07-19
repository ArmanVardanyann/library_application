package application.port.in.user;

import application.domain.dto.Book;
import application.domain.dto.BookSearchCriteria;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface SearchBookUseCase {

    List<Book> search(BookSearchCriteria criteria);
}
