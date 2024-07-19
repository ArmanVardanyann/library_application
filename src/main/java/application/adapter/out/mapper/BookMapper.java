package application.adapter.out.mapper;

import application.domain.dto.Book;
import application.domain.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookEntity mapToEntity(Book book) {

        return new BookEntity(
                book.getId(),
                book.getAuthor(),
                book.getTitle(),
                book.getPrice(),
                book.getGenre(),
                book.getPublishedAt()
        );
    }

    public Book mapToDomain(BookEntity bookEntity) {
        return Book.withId(
                bookEntity.getId(),
                bookEntity.getAuthor(),
                bookEntity.getTitle(),
                bookEntity.getPrice(),
                bookEntity.getGenre(),
                bookEntity.getPublishedAt()
        );
    }

}
