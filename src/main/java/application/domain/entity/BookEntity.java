package application.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Table(name = "book")
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEntity {

    @Id
    @UuidGenerator
    @ToString.Include
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "genre")
    private String genre;

    @Column(name = "published_at")
    private LocalDate publishedAt;

    public BookEntity(
            UUID id,
            String author,
            String title,
            BigDecimal price,
            String genre,
            LocalDate publishedAt
    ) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.genre = genre;
        this.publishedAt = publishedAt;
    }
}
