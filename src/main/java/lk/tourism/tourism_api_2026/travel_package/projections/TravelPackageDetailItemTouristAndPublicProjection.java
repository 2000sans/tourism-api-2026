package lk.tourism.tourism_api_2026.travel_package.projections;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public interface TravelPackageDetailItemTouristAndPublicProjection {

    String getTravelPackageDetailCode();
    String getDestinationTitle();
    String getDestinationDescription();
    String getGoogleMapURL();

}
