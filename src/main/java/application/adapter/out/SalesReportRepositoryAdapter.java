package application.adapter.out;

import application.domain.entity.SalesReportEntity;
import application.port.out.SalesReportRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SalesReportRepositoryAdapter implements SalesReportRepositoryPort {

    private final SalesReportEntityRepository salesReportEntityRepository;

    @Override
    public List<SalesReportEntity> getAllSales() {
        return salesReportEntityRepository.findAll();
    }

    @Override
    public List<SalesReportEntity> getSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return salesReportEntityRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public void save(SalesReportEntity salesReportEntity) {
        salesReportEntityRepository.save(salesReportEntity);
    }
}
