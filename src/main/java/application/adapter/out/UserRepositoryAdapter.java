package application.adapter.out;

import application.adapter.out.mapper.UserMapper;
import application.domain.dto.User;
import application.domain.entity.Role;
import application.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserEntityRepository userEntityRepository;

    private final UserMapper userMapper;

    @Override
    public Optional<User> readById(UUID id) {
        return userEntityRepository.findById(id)
                .map(userMapper::mapToDomain);
    }

    public List<User> readAll() {
        return userEntityRepository.findAll()
                .stream().map(userMapper::mapToDomain)
                .toList();
    }

    @Override
    public UUID save(User user) {
        return userEntityRepository.save(
                userMapper.mapToEntity(user)
        ).getId();
    }

    @Override
    public void deleteById(UUID id) {
        userEntityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return userEntityRepository.existsById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userEntityRepository.findByUsername(username)
                .map(userMapper::mapToDomain);
    }

    @Override
    public boolean existsByUsernameAndRole(String username, Set<Role> roleSet) {
        return userEntityRepository.existsByUsernameAndRolesContains(username, roleSet);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userEntityRepository.findByEmail(email)
                .map(userMapper::mapToDomain);
    }

}
