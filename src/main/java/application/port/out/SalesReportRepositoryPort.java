package application.port.out;

import application.domain.entity.SalesReportEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface SalesReportRepositoryPort {

    List<SalesReportEntity> getAllSales();

    List<SalesReportEntity> getSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    void save(SalesReportEntity salesReportEntity);
}
