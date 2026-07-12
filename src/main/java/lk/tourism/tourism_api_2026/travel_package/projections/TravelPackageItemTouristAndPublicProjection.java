package lk.tourism.tourism_api_2026.travel_package.projections;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface TravelPackageItemTouristAndPublicProjection{

    String getTravelPackageCode();
    String getTravelPackageName();
    Integer getTravelPackageMemberCount();
    String getEstimatedDuration();
    BigDecimal getTotalPrice();

}
