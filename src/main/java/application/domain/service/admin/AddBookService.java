package application.domain.service.admin;

import application.adapter.out.mapper.BookMapper;
import application.domain.dto.Book;
import application.domain.entity.BookEntity;
import application.port.in.admin.AddBookUseCase;
import application.port.out.BookRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AddBookService implements AddBookUseCase {

    private final BookRepositoryPort bookRepositoryPort;
    private final BookMapper bookMapper;

    @Override
    public UUID add(AddBookCommand command) {
        log.trace("Adding new book with command: {}", command);

        BookEntity bookEntity = BookEntity.builder()
                .author(command.getAuthor())
                .title(command.getTitle())
                .genre(command.getGenre())
                .price(command.getPrice())
                .publishedAt(command.getPublishedAt())
                .build();

        Book book = bookMapper.mapToDomain(bookEntity);

        return bookRepositoryPort.save(book);
    }
}
