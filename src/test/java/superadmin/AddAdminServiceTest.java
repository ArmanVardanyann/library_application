package superadmin;

import application.port.in.auth.SignUpUseCase;
import application.port.in.superadmin.AddAdminUseCase;
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
public class AddAdminServiceTest {

    @Autowired
    private AddAdminUseCase addAdminUseCase;

    @Autowired
    private SignUpUseCase signUpUseCase;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Test
    void addAdmin() {
        SignUpUseCase.SignUpCommand signUpCommand = new SignUpUseCase.SignUpCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                BigDecimal.ZERO,
                "testing@gmail.com"
        );

        signUpUseCase.signUp(signUpCommand);

        AddAdminUseCase.AddAdminCommand addAdminCommand = new AddAdminUseCase.AddAdminCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                "testing@gmail.com"
        );

        addAdminUseCase.add(addAdminCommand);

        Assertions.assertEquals(2, userRepositoryPort.readAll().size());
    }

    @Test
    void addExistingAdmin() {
        AddAdminUseCase.AddAdminCommand addAdminCommand = new AddAdminUseCase.AddAdminCommand(
                "test",
                "testPassword",
                "firstName",
                "lastName",
                "testing@gmail.com"
        );

        addAdminUseCase.add(addAdminCommand);

        Assertions.assertThrows(
                UserAlreadyExistsException.class,
                () -> addAdminUseCase.add(
                        addAdminCommand
                )
        );
    }
}
