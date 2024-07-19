package application.port.in.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Validated
public interface AddBookUseCase {

    UUID add(@Valid AddBookCommand command);

    @Data
    class AddBookCommand{

        @NotEmpty
        private final String author;

        @NotEmpty
        private final String title;

        @NotEmpty
        private final String genre;

        @Positive
        private final BigDecimal price;

        @PastOrPresent
        private final LocalDate publishedAt;
    }
}
