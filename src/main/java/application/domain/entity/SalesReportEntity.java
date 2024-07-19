package application.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "sales_report")
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SalesReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "book_id")
    private UUID bookId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public SalesReportEntity(
            UUID id,
            UUID userId,
            UUID bookId,
            BigDecimal amount,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
