package application.domain.service.auth;

import application.adapter.out.mapper.BookMapper;
import application.domain.dto.Book;
import application.domain.dto.User;
import application.port.in.auth.SuggestBookUseCase;
import application.port.out.BookRepositoryPort;
import application.port.out.UserRepositoryPort;
import common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SuggestBookService implements SuggestBookUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final BookRepositoryPort bookRepositoryPort;
    private final BookMapper bookMapper;

    @Override
    public List<Book> suggest(UUID userId) {

        User user = userRepositoryPort.readById(userId)
                .orElseThrow(
                        () -> new NotFoundException("User not found with id: " + userId)
                );

        Set<String> purchasedGenres = user.getPurchasedBooks().stream()
                .map(bookMapper::mapToDomain)
                .map(Book::getGenre)
                .collect(Collectors.toSet());

        return bookRepositoryPort.readAll().stream()
                .filter(book -> purchasedGenres.contains(book.getGenre())
                        && !user.getPurchasedBooks().contains(bookMapper.mapToEntity(book))
                )
                .toList();
    }
}
