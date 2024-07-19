package admin;

import application.port.in.admin.AddBookUseCase;
import application.port.in.admin.DeleteBookUseCase;
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
public class DeleteBookServiceTest {

    @Autowired
    private DeleteBookUseCase deleteBookUseCase;

    @Autowired
    private AddBookUseCase addBookUseCase;

    @Autowired
    private BookRepositoryPort bookRepositoryPort;

    @Test
    void delete() {
        AddBookUseCase.AddBookCommand command = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Fall",
                "Existential Fiction",
                BigDecimal.valueOf(20),
                LocalDate.of(1956, 10, 15)
        );

        UUID bookId = addBookUseCase.add(command);

        Assertions.assertEquals(1, bookRepositoryPort.readAll().size());

        deleteBookUseCase.deleteById(bookId);

        Assertions.assertEquals(0, bookRepositoryPort.readAll().size());
        Assertions.assertTrue(bookRepositoryPort.readById(bookId).isEmpty());
    }
}
