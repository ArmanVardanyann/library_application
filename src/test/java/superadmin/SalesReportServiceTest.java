package superadmin;

import application.domain.dto.User;
import application.domain.entity.SalesReportEntity;
import application.port.in.admin.AddBookUseCase;
import application.port.in.auth.SignUpUseCase;
import application.port.in.superadmin.SalesReportUseCase;
import application.port.in.user.PurchaseBookUseCase;
import application.port.out.BookRepositoryPort;
import application.port.out.UserRepositoryPort;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = LibraryApplication.class, properties = {"spring.config.location = classpath:application-test.yml"})
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SalesReportServiceTest {

    @Autowired
    private SalesReportUseCase salesReportUseCase;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Autowired
    private SignUpUseCase signUpUseCase;

    @Autowired
    private AddBookUseCase addBookUseCase;

    @Autowired
    private PurchaseBookUseCase purchaseBookUseCase;

    @Autowired
    private BookRepositoryPort bookRepositoryPort;

    @Test
    void getAllReports() {

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

        List<SalesReportEntity> sales = salesReportUseCase.getAllSales();

        Assertions.assertEquals(1, sales.size());
    }

    @Test
    void getReportsBetweenDates() {

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

        List<SalesReportEntity> sales = salesReportUseCase.getSalesBetweenDates(
                LocalDateTime.now().minusDays(4),
                LocalDateTime.now().minusHours(1)
        );

        Assertions.assertEquals(0, sales.size());
    }
}
