package application.domain.service.superadmin;

import application.domain.entity.SalesReportEntity;
import application.port.in.superadmin.SalesReportUseCase;
import application.port.out.SalesReportRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class SalesReportService implements SalesReportUseCase {

    private final SalesReportRepositoryPort salesReportRepositoryPort;

    @Override
    public List<SalesReportEntity> getAllSales() {
        log.trace("Loading reports of ever sales");

        return salesReportRepositoryPort.getAllSales();
    }

    @Override
    public List<SalesReportEntity> getSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        log.trace("Loading sales reports from : {} to {}", startDate, endDate);

        return salesReportRepositoryPort.getSalesBetweenDates(startDate, endDate);
    }

}
