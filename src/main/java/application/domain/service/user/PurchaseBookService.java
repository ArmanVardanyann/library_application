package application.domain.service.user;

import application.adapter.out.mapper.BookMapper;
import application.domain.dto.Book;
import application.domain.dto.User;
import application.domain.entity.Role;
import application.domain.entity.SalesReportEntity;
import application.port.in.user.PurchaseBookUseCase;
import application.port.out.BookRepositoryPort;
import application.port.out.SalesReportRepositoryPort;
import application.port.out.UserRepositoryPort;
import common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utility.RoleName;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PurchaseBookService implements PurchaseBookUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final BookRepositoryPort bookRepositoryPort;
    private final SalesReportRepositoryPort salesReportRepositoryPort;
    private final BookMapper bookMapper;


    @Override
    public void purchase(UUID userId, UUID bookId) {
        log.trace("Starting process of purchasing the book for user with id: {} and book with id: {}", userId, bookId);

        User user = userRepositoryPort.readById(userId)
                .orElseThrow(
                        () -> new NotFoundException("User not found with id: " + userId)
                );

        Book book = bookRepositoryPort.readById(bookId)
                .orElseThrow(
                        () -> new NotFoundException("Book not found with id: " + bookId)
                );

        List<RoleName> userRoleNames = user.getRoles().stream().map(Role::getName).toList();

        if (!userRoleNames.contains(RoleName.ROLE_USER)) {
            throw new RuntimeException("User does not have the necessary role to purchase books");
        }

        user.purchaseBook(bookMapper.mapToEntity(book));

        salesReportRepositoryPort.save(
                SalesReportEntity.builder()
                        .userId(userId)
                        .bookId(bookId)
                        .amount(book.getPrice())
                        .createdAt(LocalDateTime.now())
                        .build()
                );

        log.trace("Book purchase successfully completed!");
        userRepositoryPort.save(user);
    }
}
