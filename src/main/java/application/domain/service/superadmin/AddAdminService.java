package application.domain.service.superadmin;

import application.adapter.out.RoleRepositoryPort;
import application.adapter.out.mapper.UserMapper;
import application.domain.dto.User;
import application.domain.entity.Role;
import application.domain.entity.UserEntity;
import application.port.in.superadmin.AddAdminUseCase;
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
@Transactional
@RequiredArgsConstructor
public class AddAdminService implements AddAdminUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User add(AddAdminCommand command) {
        log.trace("Adding new admin with command: {}", command);

        Optional<Role> roleOptional = roleRepositoryPort.findByName(RoleName.ROLE_ADMIN);

        Role adminRole = roleOptional.orElseGet(() -> {
            Role role = Role.builder()
                    .name(RoleName.ROLE_ADMIN)
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .build();
            return roleRepositoryPort.save(role);
        });

        if (userRepositoryPort.existsByUsernameAndRole(command.getUsername(), Set.of(adminRole))) {
            throw new UserAlreadyExistsException("Admin with the same username already exists!");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(command.getEmail())
                .roles(Set.of(adminRole))
                .build();

        User user = userMapper.mapToDomain(userEntity);

        userRepositoryPort.save(user);

        return user;
    }
}
