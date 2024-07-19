package user;

import application.domain.dto.Book;
import application.domain.dto.BookSearchCriteria;
import application.port.in.admin.AddBookUseCase;
import application.port.in.user.SearchBookUseCase;
import com.example.libraryapplication.LibraryApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = LibraryApplication.class, properties = {"spring.config.location = classpath:application-test.yml"})
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SearchBookServiceTest {

    @Autowired
    private SearchBookUseCase searchBookUseCase;

    @Autowired
    private AddBookUseCase addBookUseCase;

    @Test
    void searchBooks() {
        AddBookUseCase.AddBookCommand addBookCommand = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Fall",
                "Existential Fiction",
                BigDecimal.valueOf(20),
                LocalDate.of(1956, 10, 15)
        );

        AddBookUseCase.AddBookCommand addSecondBookCommand = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Plague",
                "Novel",
                BigDecimal.valueOf(40),
                LocalDate.of(1947, 10, 15)
        );

        AddBookUseCase.AddBookCommand addThirdBookCommand = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Stranger",
                "Philosophical Fiction",
                BigDecimal.valueOf(20),
                LocalDate.of(1942, 10, 15)
        );

        addBookUseCase.add(addBookCommand);
        addBookUseCase.add(addSecondBookCommand);
        addBookUseCase.add(addThirdBookCommand);

        List<Book> booksOfAlbertCamus = searchBookUseCase.search(BookSearchCriteria.builder()
                .author("Albert Camus")
                .build());

        List<Book> theFallBook = searchBookUseCase.search(BookSearchCriteria.builder()
                .title("The Fall")
                .build());

        List<Book> booksCheaperOrEqualThan30 = searchBookUseCase.search(BookSearchCriteria.builder()
                .maxPrice(BigDecimal.valueOf(30))
                .build());

        List<Book> allBooks = searchBookUseCase.search(BookSearchCriteria.builder()
                .build());

        Assertions.assertEquals(1, theFallBook.size());
        Assertions.assertEquals(2, booksCheaperOrEqualThan30.size());
        Assertions.assertEquals(3, booksOfAlbertCamus.size());
        Assertions.assertEquals(3, allBooks.size());
    }
}
