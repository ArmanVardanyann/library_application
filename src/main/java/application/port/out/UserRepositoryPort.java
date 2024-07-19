package application.port.out;

import application.domain.dto.User;
import application.domain.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepositoryPort {

    Optional<User> readById(UUID id);

    List<User> readAll();

    UUID save(User user);

    void deleteById(UUID id);

    boolean existsById(UUID id);

    Optional<User> findByUsername(String username);

    boolean existsByUsernameAndRole(String username, Set<Role> roleSet);

    Optional<User> findByEmail(String email);

}
