package lk.tourism.tourism_api_2026.travel_package.dto;

import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterTravelPackageItemsRequest {

    private String name;
    private Integer memberCount;
    private String estimatedDuration;
    private BigDecimal totalPrice;
    private Integer sectionNumber;
    private Integer sectionSize;

}
