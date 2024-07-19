package admin;

import application.domain.dto.User;
import application.port.in.admin.ReadUserUseCase;
import application.port.in.auth.SignUpUseCase;
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

@SpringBootTest(classes = LibraryApplication.class, properties = {"spring.config.location = classpath:application-test.yml"})
@ContextConfiguration
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReadUserServiceTest {

    @Autowired
    private ReadUserUseCase readUserUseCase;

    @Autowired
    private SignUpUseCase signUpUseCase;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Test
    void getUserById() {
        SignUpUseCase.SignUpCommand command = new SignUpUseCase.SignUpCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.ZERO,
                "testing@gmail.com"
        );

        signUpUseCase.signUp(command);

        User signedUpUser = userRepositoryPort.findByUsername("test").get();

        Assertions.assertNotNull(signedUpUser);

        User fetchedUser = readUserUseCase.readById(signedUpUser.getId());

        Assertions.assertNotNull(fetchedUser);
        Assertions.assertEquals(signedUpUser.getUsername(), fetchedUser.getUsername());
    }

    @Test
    void getAllUsers() {

        Assertions.assertEquals(0, readUserUseCase.readAll().size());

        SignUpUseCase.SignUpCommand command = new SignUpUseCase.SignUpCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.ZERO,
                "testing@gmail.com"
        );

        signUpUseCase.signUp(command);

        Assertions.assertEquals(1, readUserUseCase.readAll().size());
    }
}
