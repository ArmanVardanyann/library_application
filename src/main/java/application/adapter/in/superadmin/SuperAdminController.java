package application.adapter.in.superadmin;

import application.domain.dto.User;
import application.domain.entity.SalesReportEntity;
import application.port.in.superadmin.AddAdminUseCase;
import application.port.in.superadmin.SalesReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/super-admin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final AddAdminUseCase addAdminUseCase;
    private final SalesReportUseCase salesReportUseCase;

    @PostMapping("/add-admin")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<User> addAdmin(@RequestBody AddAdminUseCase.AddAdminCommand command) {
        User user = addAdminUseCase.add(command);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/sales-reports")
    public ResponseEntity<List<SalesReportEntity>> getAllSales() {
        List<SalesReportEntity> sales = salesReportUseCase.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/sales-reports/date-range")
    public ResponseEntity<List<SalesReportEntity>> getSalesByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<SalesReportEntity> sales = salesReportUseCase.getSalesBetweenDates(startDate, endDate);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

}
