package admin;

import application.domain.dto.Book;
import application.port.in.admin.AddBookUseCase;
import application.port.in.admin.ReadBookUseCase;
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
import java.util.UUID;

@SpringBootTest(classes = LibraryApplication.class, properties = {"spring.config.location = classpath:application-test.yml"})
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ReadBookServiceTest {

    @Autowired
    private ReadBookUseCase readBookUseCase;

    @Autowired
    private AddBookUseCase addBookUseCase;

    @Test
    void getBookById() {
        AddBookUseCase.AddBookCommand command = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Fall",
                "Existential Fiction",
                BigDecimal.valueOf(20),
                LocalDate.of(1956, 10, 15)
        );

        UUID bookId = addBookUseCase.add(command);

        Book book = readBookUseCase.readById(bookId);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(command.getAuthor(), book.getAuthor());
    }

    @Test
    void getAllBooks() {

        Assertions.assertEquals(0, readBookUseCase.readAll().size());

        AddBookUseCase.AddBookCommand command = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Fall",
                "Existential Fiction",
                BigDecimal.valueOf(20),
                LocalDate.of(1956, 10, 15)
        );

        addBookUseCase.add(command);

        List<Book> bookList = readBookUseCase.readAll();

        Assertions.assertNotNull(bookList);
        Assertions.assertEquals(1, bookList.size());
    }
}
