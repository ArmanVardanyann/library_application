package application.adapter.out;

import application.adapter.out.mapper.BookMapper;
import application.domain.dto.Book;
import application.domain.dto.BookSearchCriteria;
import application.domain.entity.BookEntity;
import application.domain.entity.QBookEntity;
import application.port.out.BookRepositoryPort;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepositoryPort {

    private final BookEntityRepository bookEntityRepository;
    private final BookMapper bookMapper;

    @Override
    public List<Book> readAll() {
        return bookEntityRepository.findAll()
                .stream().map(bookMapper::mapToDomain)
                .toList();
    }

    @Override
    public Optional<Book> readById(UUID id) {
        return bookEntityRepository.findById(id)
                .map(bookMapper::mapToDomain);
    }

    @Override
    public UUID save(Book book) {
        return bookEntityRepository.save(
                bookMapper.mapToEntity(book)
        ).getId();
    }

    @Override
    public void deleteById(UUID id) {
        bookEntityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return bookEntityRepository.existsById(id);
    }

    @Override
    public List<Book> searchBooks(BookSearchCriteria criteria) {
        QBookEntity book = QBookEntity.bookEntity;

        BooleanBuilder predicate = new BooleanBuilder();

        if (criteria.getTitle() != null) {
            predicate.and(book.title.containsIgnoreCase(criteria.getTitle()));
        }

        if (criteria.getAuthor() != null) {
            predicate.and(book.author.containsIgnoreCase(criteria.getAuthor()));
        }

        if (criteria.getGenre() != null) {
            predicate.and(book.genre.containsIgnoreCase(criteria.getGenre()));
        }

        if (criteria.getMinPrice() != null) {
            predicate.and(book.price.goe(criteria.getMinPrice()));
        }

        if (criteria.getMaxPrice() != null) {
            predicate.and(book.price.loe(criteria.getMaxPrice()));
        }

        if (criteria.getStartDate() != null) {
            predicate.and(book.publishedAt.goe(criteria.getStartDate()));
        }

        if (criteria.getEndDate() != null) {
            predicate.and(book.publishedAt.loe(criteria.getEndDate()));
        }

        Iterable<BookEntity> bookEntities = bookEntityRepository.findAll(predicate);

        return StreamSupport.stream(bookEntities.spliterator(), false)
                .map(bookMapper::mapToDomain)
                .collect(Collectors.toList());
    }
}
