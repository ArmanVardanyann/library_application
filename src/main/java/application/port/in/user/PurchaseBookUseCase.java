package application.port.in.user;

import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface PurchaseBookUseCase {

    void purchase(UUID userId, UUID bookId);
}
