package application.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class BookSearchCriteria {

    private String title;
    private String author;
    private String genre;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private LocalDate startDate;
    private LocalDate endDate;
}
