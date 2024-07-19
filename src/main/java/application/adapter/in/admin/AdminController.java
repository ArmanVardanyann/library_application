package application.adapter.in.admin;

import application.domain.dto.Book;
import application.domain.dto.User;
import application.port.in.admin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ReadUserUseCase readUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ReadBookUseCase readBookUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;
    private final AddBookUseCase addBookUseCase;

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = readUserUseCase.readAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {

        User user = readUserUseCase.readById(id);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateUserById(@PathVariable UUID id, @RequestBody UpdateUserUseCase.UpdateUserCommand command) {
        updateUserUseCase.update(id, command);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        deleteUserUseCase.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/books")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> books = readBookUseCase.readAll();

        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Book> getAllBooks(@PathVariable UUID id) {

        Book book = readBookUseCase.readById(id);

        return ResponseEntity.ok(book);
    }

    @PostMapping("/books")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UUID> addBook(@RequestBody AddBookUseCase.AddBookCommand command) {

        UUID bookId = addBookUseCase.add(command);

        return ResponseEntity.ok(bookId);
    }

    @PutMapping("/books/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateBookById(@PathVariable UUID id, @RequestBody UpdateBookUseCase.UpdateBookCommand command) {
        updateBookUseCase.update(id, command);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/books/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
        deleteBookUseCase.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
