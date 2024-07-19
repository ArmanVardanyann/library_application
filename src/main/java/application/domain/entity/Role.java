package application.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import utility.RoleName;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@Table(name = "role")
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {

    @Id
    @UuidGenerator
    @ToString.Include
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", updatable = false, nullable = false)
    private RoleName name;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Set<Permission> permissions;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    private Role(UUID id, RoleName name, Set<Permission> permissions, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
