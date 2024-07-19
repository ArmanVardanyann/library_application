package application.domain.service.auth;

import application.domain.dto.User;
import application.port.in.auth.SignInUseCase;
import application.port.out.UserRepositoryPort;
import common.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SignInService implements SignInUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signIn(SignInCommand command) {
        log.trace("Authenticating the user with the command: {}", command);

        User user = userRepositoryPort.findByUsername(command.getUsername())
                .orElseThrow(
                        () -> new AuthenticationException("Invalid username or password")
                );

        if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid username or password");
        }

        return user;
    }
}
