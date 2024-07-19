package application.adapter.in.user;

import application.domain.dto.Book;
import application.domain.dto.BookSearchCriteria;
import application.port.in.user.PurchaseBookUseCase;
import application.port.in.user.SearchBookUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PurchaseBookUseCase purchaseBookUseCase;
    private final SearchBookUseCase searchBookUseCase;

    @PostMapping("/purchase/{id}/{bookId}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<Void> purchaseBook(@PathVariable UUID id, @PathVariable UUID bookId) {
        purchaseBookUseCase.purchase(id, bookId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<List<Book>> searchBooks(BookSearchCriteria criteria) {
        List<Book> books = searchBookUseCase.search(criteria);
        return ResponseEntity.ok(books);
    }
}
