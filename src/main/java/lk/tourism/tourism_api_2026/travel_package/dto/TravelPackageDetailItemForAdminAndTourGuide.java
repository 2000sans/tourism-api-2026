package lk.tourism.tourism_api_2026.travel_package.dto;

import lombok.Builder;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TravelPackageDetailItemForAdminAndTourGuide {

    private String travelPackageDetailCode;
    private String destinationTitle;
    private String destinationDescription;
    private String googleMapURL;

}
