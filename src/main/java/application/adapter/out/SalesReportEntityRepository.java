package application.adapter.out;

import application.domain.entity.SalesReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SalesReportEntityRepository extends JpaRepository<SalesReportEntity, UUID> {

    List<SalesReportEntity> findByUserId(UUID userId);

    List<SalesReportEntity> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
