package auth;

import application.domain.dto.User;
import application.port.in.auth.SignUpUseCase;
import application.port.out.UserRepositoryPort;
import com.example.libraryapplication.LibraryApplication;
import common.exception.UserAlreadyExistsException;
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
class SignUpServiceTest {

    @Autowired
    private SignUpUseCase signUpUseCase;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Test
    void signUpAndFetchUser() {

        SignUpUseCase.SignUpCommand command = new SignUpUseCase.SignUpCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.ZERO,
                "testing@gmail.com"
        );

        User user = signUpUseCase.signUp(command);

        User fetchedUser = userRepositoryPort.findByUsername(user.getUsername()).get();

        Assertions.assertNotNull(fetchedUser);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getUsername(), fetchedUser.getUsername());
        Assertions.assertEquals(user.getEmail(), fetchedUser.getEmail());

        Assertions.assertEquals(1, userRepositoryPort.readAll().size());
    }

    @Test
    void existingUserSignUp() {
        SignUpUseCase.SignUpCommand command = new SignUpUseCase.SignUpCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.ZERO,
                "testing@gmail.com"
        );

        signUpUseCase.signUp(command);

        Assertions.assertThrows(
                UserAlreadyExistsException.class,
                () -> signUpUseCase.signUp(
                        command
                )
        );
    }
}