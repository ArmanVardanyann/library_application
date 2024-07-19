package application.port.in.superadmin;

import application.domain.entity.SalesReportEntity;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Validated
public interface SalesReportUseCase {

    List<SalesReportEntity> getAllSales();

    List<SalesReportEntity> getSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
