package application.adapter.in.auth;

import application.domain.dto.Book;
import application.domain.dto.User;
import application.port.in.auth.SignInUseCase;
import application.port.in.auth.SignUpUseCase;
import application.port.in.auth.SuggestBookUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpUseCase signupUseCase;
    private final SignInUseCase signInUseCase;
    private final SuggestBookUseCase suggestBookUseCase;

    @PostMapping("/sign-up")
    public ResponseEntity<User> registerUser(@RequestBody SignUpUseCase.SignUpCommand command) {
        User user = signupUseCase.signUp(command);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> authenticateUser(@RequestBody SignInUseCase.SignInCommand command) {
        signInUseCase.signIn(command);
        return ResponseEntity.ok("Authentication successful");
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<Book>> suggestBooks(@RequestParam UUID userId) {
        List<Book> suggestedBooks = suggestBookUseCase.suggest(userId);
        return ResponseEntity.ok(suggestedBooks);
    }
}

