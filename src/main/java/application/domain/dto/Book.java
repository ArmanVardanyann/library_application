package application.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Book {

    private final UUID id;

    @Setter
    private String author;

    @Setter
    private String title;

    @Setter
    private BigDecimal price;

    @Setter
    private String genre;

    @Setter
    private LocalDate publishedAt;

    public Book(UUID id, String author, String title, BigDecimal price, String genre, LocalDate publishedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.genre = genre;
        this.publishedAt = publishedAt;
    }

    public Book(Book book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.genre = book.getGenre();
        this.publishedAt = book.getPublishedAt();
    }

    public static Book withId(
            UUID id,
            String author,
            String title,
            BigDecimal price,
            String genre,
            LocalDate publishedAt
    ) {
        return new Book(
                id,
                author,
                title,
                price,
                genre,
                publishedAt
        );
    }

    public static Book withoutId(
            String author,
            String title,
            BigDecimal price,
            String genre,
            LocalDate publishedAt
    ) {
        return new Book(
                null,
                author,
                title,
                price,
                genre,
                publishedAt
        );
    }
}
