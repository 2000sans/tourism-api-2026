package lk.tourism.tourism_api_2026.travel_package.dto;

import lk.tourism.tourism_api_2026.travel_package.model.enums.TravelPackageStatus;
import lombok.Builder;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TravelPackageItemWithCode {

    private String travelPackageCode;
    private String name;
    private Integer memberCount;
    private String estimatedDuration;
    private BigDecimal totalPrice;
    private TravelPackageStatus travelPackageStatus;
    private List<TravelPackageDetailItemWithCode> visitingLocations;

}
