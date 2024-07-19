package application.domain.service.auth;

import application.adapter.out.RoleRepositoryPort;
import application.adapter.out.mapper.UserMapper;
import application.domain.dto.User;
import application.domain.entity.Role;
import application.domain.entity.UserEntity;
import application.port.in.auth.SignUpUseCase;
import application.port.out.UserRepositoryPort;
import common.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utility.RoleName;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SignUpService implements SignUpUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User signUp(SignUpCommand command) {
        log.trace("Signing up a user with command: {}", command);

        Optional<Role> roleOptional = roleRepositoryPort.findByName(RoleName.ROLE_USER);

        Role userRole = roleOptional.orElseGet(() -> {
            Role role = Role.builder()
                    .name(RoleName.ROLE_USER)
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .build();
            return roleRepositoryPort.save(role);
        });

        if (userRepositoryPort.existsByUsernameAndRole(command.getUsername(), Set.of(userRole))) {
            throw new UserAlreadyExistsException("User already exists");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .accountBalance(command.getAccountBalance())
                .roles(Set.of(userRole))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

        User user = userMapper.mapToDomain(userEntity);

        userRepositoryPort.save(user);

        return user;
    }
}
