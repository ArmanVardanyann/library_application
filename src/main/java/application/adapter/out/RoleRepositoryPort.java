package application.adapter.out;

import application.domain.entity.Role;
import utility.RoleName;

import java.util.Optional;

public interface RoleRepositoryPort {

    Optional<Role> findByName(RoleName roleName);

    Role save(Role role);
}
