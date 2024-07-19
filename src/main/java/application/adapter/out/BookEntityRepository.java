package application.adapter.out;

import application.domain.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, UUID>, QuerydslPredicateExecutor<BookEntity> {
}
