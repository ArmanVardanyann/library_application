package application.domain.dto;

import application.domain.entity.BookEntity;
import application.domain.entity.Role;
import common.exception.InsufficientBalanceException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class User extends FlatUser {

    private final Set<Role> roles;

    private final Set<BookEntity> purchasedBooks;

    protected User(UUID id,
                   String username,
                   String password,
                   String firstName,
                   String lastName,
                   String email,
                   BigDecimal accountBalance,
                   Set<Role> roles,
                   Set<BookEntity> purchasedBooks,
                   LocalDateTime createdAt,
                   LocalDateTime modifiedAt) {
        super(id, username, password, firstName, lastName, email, accountBalance, createdAt, modifiedAt);
        this.roles = roles;
        this.purchasedBooks = purchasedBooks;
    }

    public static User withId(
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
        return new User(
                id,
                username,
                password,
                firstName,
                lastName,
                email,
                accountBalance,
                roles,
                purchasedBooks,
                createdAt,
                modifiedAt
        );
    }

    public static User withoutId(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            BigDecimal accountBalance,
            Set<Role> roles,
            Set<BookEntity> purchasedBooks,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        return new User(
                null,
                username,
                password,
                firstName,
                lastName,
                email,
                accountBalance,
                roles,
                purchasedBooks,
                createdAt,
                modifiedAt
        );
    }

    public void purchaseBook(BookEntity book) {
        if (this.accountBalance.compareTo(book.getPrice()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to purchase the book");
        }
        this.accountBalance = this.accountBalance.subtract(book.getPrice());
        this.purchasedBooks.add(book);
    }
}
