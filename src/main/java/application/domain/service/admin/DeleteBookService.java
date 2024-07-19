package application.domain.service.admin;

import application.port.in.admin.DeleteBookUseCase;
import application.port.out.BookRepositoryPort;
import common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class DeleteBookService implements DeleteBookUseCase {

    private final BookRepositoryPort bookRepositoryPort;

    @Override
    public void deleteById(UUID id) {
        log.trace("Deleting the book with specified id: {}", id);

        if(!bookRepositoryPort.existsById(id)){
            throw new NotFoundException("Book is not found with the provided id: " + id);
        }

        bookRepositoryPort.deleteById(id);
    }
}
