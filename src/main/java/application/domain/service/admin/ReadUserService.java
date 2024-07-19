package application.domain.service.admin;

import application.domain.dto.User;
import application.port.in.admin.ReadUserUseCase;
import application.port.out.UserRepositoryPort;
import common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadUserService implements ReadUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User readById(UUID id) {
        log.trace("Getting user with id: {}", id);

        return userRepositoryPort.readById(id)
                .orElseThrow(() ->
                        new NotFoundException("User not found with specified id: " + id)
                );
    }

    @Override
    public List<User> readAll() {
        log.trace("Fetching all the users");

        return userRepositoryPort.readAll();
    }
}
