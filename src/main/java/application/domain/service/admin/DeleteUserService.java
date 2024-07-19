package application.domain.service.admin;

import application.port.in.admin.DeleteUserUseCase;
import application.port.out.UserRepositoryPort;
import common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void deleteById(UUID id) {
        log.trace("Deleting user with id : {}", id);

        if (!userRepositoryPort.existsById(id)) {
            throw new NotFoundException("User with provided id " + id + " not found.");
        }

        userRepositoryPort.deleteById(id);
    }
}
