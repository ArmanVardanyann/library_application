package admin;

import application.domain.dto.User;
import application.port.in.admin.DeleteUserUseCase;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class DeleteUserServiceTest {

    @Autowired
    private SignUpUseCase signUpUseCase;

    @Autowired
    private DeleteUserUseCase deleteUserUseCase;

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Test
    void deleteUserById(){
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
        Assertions.assertTrue(userRepositoryPort.existsById(signedUpUser.getId()));

        deleteUserUseCase.deleteById(signedUpUser.getId());

        Assertions.assertFalse(userRepositoryPort.existsById(signedUpUser.getId()));

    }
}
