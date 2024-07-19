package user;

import application.domain.dto.User;
import application.port.in.admin.AddBookUseCase;
import application.port.in.auth.SignUpUseCase;
import application.port.in.user.PurchaseBookUseCase;
import application.port.out.UserRepositoryPort;
import com.example.libraryapplication.LibraryApplication;
import common.exception.InsufficientBalanceException;
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
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PurchaseBookServiceTest {

    @Autowired
    private PurchaseBookUseCase purchaseBookUseCase;

    @Autowired
    private SignUpUseCase signUpUseCase;

    @Autowired
    private AddBookUseCase addBookUseCase;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Test
    void purchaseBook() {

        SignUpUseCase.SignUpCommand signUpCommand = new SignUpUseCase.SignUpCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.valueOf(20),
                "testing@gmail.com"
        );

        signUpUseCase.signUp(signUpCommand);

        User user = userRepositoryPort.findByUsername(signUpCommand.getUsername()).get();

        AddBookUseCase.AddBookCommand addBookCommand = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Fall",
                "Existential Fiction",
                BigDecimal.valueOf(20),
                LocalDate.of(1956, 10, 15)
        );

        UUID bookId = addBookUseCase.add(addBookCommand);

        purchaseBookUseCase.purchase(user.getId(), bookId);

        User updatedUser = userRepositoryPort.findByUsername(
                signUpCommand.getUsername()
        ).get();

        Assertions.assertEquals(1, updatedUser.getPurchasedBooks().size());
    }

    @Test
    void notEnoughFundsToPurchaseBook() {
        SignUpUseCase.SignUpCommand signUpCommand = new SignUpUseCase.SignUpCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.ZERO,
                "testing@gmail.com"
        );

        signUpUseCase.signUp(signUpCommand);

        User user = userRepositoryPort.findByUsername(signUpCommand.getUsername()).get();

        AddBookUseCase.AddBookCommand addBookCommand = new AddBookUseCase.AddBookCommand(
                "Albert Camus",
                "The Fall",
                "Existential Fiction",
                BigDecimal.valueOf(20),
                LocalDate.of(1956, 10, 15)
        );

        UUID bookId = addBookUseCase.add(addBookCommand);

        Assertions.assertThrows(
                InsufficientBalanceException.class,
                () -> purchaseBookUseCase.purchase(
                        user.getId(),
                        bookId
                )
        );
    }
}