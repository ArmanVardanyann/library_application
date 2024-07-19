package admin;

import application.domain.dto.Book;
import application.port.in.admin.AddBookUseCase;
import application.port.out.BookRepositoryPort;
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
import java.util.UUID;

@SpringBootTest(classes = LibraryApplication.class, properties = {"spring.config.location = classpath:application-test.yml"})
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AddBookServiceTest {

    @Autowired
    private AddBookUseCase addBookUseCase;

    @Autowired
    private BookRepositoryPort bookRepositoryPort;

    @Test
    void addBook() {
        AddBookUseCase.AddBookCommand command = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Fall",
                "Existential Fiction",
                BigDecimal.valueOf(20),
                LocalDate.of(1956, 10, 15)
        );

        UUID bookId = addBookUseCase.add(command);

        Book book = bookRepositoryPort.readById(bookId).get();

        Assertions.assertNotNull(book);
        Assertions.assertEquals(command.getAuthor(), book.getAuthor());
        Assertions.assertEquals(command.getTitle(), book.getTitle());
        Assertions.assertEquals(1, bookRepositoryPort.readAll().size());
    }
}
