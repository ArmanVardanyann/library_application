package application.adapter.out;

import application.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import utility.RoleName;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
