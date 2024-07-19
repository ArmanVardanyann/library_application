package application.port.in.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Validated
public interface UpdateBookUseCase {

    void update(UUID id,@Valid UpdateBookCommand command);

    @Data
    class UpdateBookCommand {

        @NotEmpty
        private final String author;

        @NotEmpty
        private final String title;

        @NotEmpty
        private final String genre;

        @Positive
        private final BigDecimal price;

        @NotNull
        @PastOrPresent
        private final LocalDate publishedAt;
    }
}
