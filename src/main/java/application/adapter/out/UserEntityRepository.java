package application.adapter.out;

import application.domain.entity.Role;
import application.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsernameAndRolesContains(String username, Set<Role> roles);

    Optional<UserEntity> findByEmail(String email);

}
