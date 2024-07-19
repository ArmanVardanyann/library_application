package application.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Table(name = "permission")
@AllArgsConstructor(staticName = "of")
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Permission {

    @Id
    @Column(name = "id")
    @ToString.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    @ToString.Include
    private String code;

    @Column(name = "name")
    @ToString.Include
    @Setter
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }

        Permission that = (Permission) o;

        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
