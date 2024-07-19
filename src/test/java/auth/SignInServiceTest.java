package auth;

import application.domain.dto.User;
import application.port.in.auth.SignInUseCase;
import application.port.in.auth.SignUpUseCase;
import application.port.out.UserRepositoryPort;
import com.example.libraryapplication.LibraryApplication;
import common.exception.AuthenticationException;
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
public class SignInServiceTest {

    @Autowired
    private SignInUseCase signInUseCase;

    @Autowired
    private SignUpUseCase signUpUseCase;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Test
    void signIn() {
        SignUpUseCase.SignUpCommand signUpCommand = new SignUpUseCase.SignUpCommand(
                "testusername",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.ZERO,
                "testing@gmail.com"
        );

        User signedUpUser = signUpUseCase.signUp(signUpCommand);

        SignInUseCase.SignInCommand command = new SignInUseCase.SignInCommand(
                "testusername",
                "testPassword"
        );

        User signedInUser = signInUseCase.signIn(command);

        Assertions.assertNotNull(signedInUser);
        Assertions.assertNotNull(signedUpUser);
        Assertions.assertEquals(signedUpUser.getPassword(), signedInUser.getPassword());
        Assertions.assertEquals(1, userRepositoryPort.readAll().size());
    }

    @Test
    void invalidSignIn() {
        SignUpUseCase.SignUpCommand signUpCommand = new SignUpUseCase.SignUpCommand(
                "testusername",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.ZERO,
                "testing@gmail.com"
        );

        signUpUseCase.signUp(signUpCommand);

        SignInUseCase.SignInCommand command = new SignInUseCase.SignInCommand(
                "testusername",
                "invalidPassword"
        );

        Assertions.assertThrows(
                AuthenticationException.class,
                () -> signInUseCase.signIn(
                        command
                )
        );
    }
}
