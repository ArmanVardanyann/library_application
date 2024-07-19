package application.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "users")
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @UuidGenerator
    @ToString.Include
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "username")
    @ColumnTransformer(write = "LOWER(?)")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<BookEntity> purchasedBooks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public UserEntity(
            UUID id,
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            Set<Role> roles,
            BigDecimal accountBalance,
            Set<BookEntity> purchasedBooks,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.accountBalance = accountBalance != null ? accountBalance : BigDecimal.ZERO;
        this.purchasedBooks = purchasedBooks;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
