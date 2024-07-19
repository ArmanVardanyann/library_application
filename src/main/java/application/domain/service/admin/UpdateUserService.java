package application.domain.service.admin;

import application.domain.dto.User;
import application.port.in.admin.UpdateUserUseCase;
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
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void update(UUID uuid, UpdateUserCommand updateUserCommand) {
        log.trace("Updating user with id : {} and with command : {}", uuid, updateUserCommand);

        User user = userRepositoryPort.readById(uuid)
                .orElseThrow(() ->
                        new NotFoundException("User not found with specified id: " + uuid)
                );

        user.setFirstName(updateUserCommand.getFirstName());
        user.setLastName(updateUserCommand.getLastName());
        user.setEmail(updateUserCommand.getEmail());

        userRepositoryPort.save(user);
    }
}
