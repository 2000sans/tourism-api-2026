package lk.tourism.tourism_api_2026.travel_package.dto;

import lombok.Builder;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TravelPackageDetailItemForPublicAndTourist implements Serializable {

    private String travelPackageDetailCode;
    private String destinationTitle;
    private String destinationDescription;
    private String googleMapURL;

}
