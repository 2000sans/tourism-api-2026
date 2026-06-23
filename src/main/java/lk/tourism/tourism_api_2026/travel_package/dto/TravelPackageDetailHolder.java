package lk.tourism.tourism_api_2026.travel_package.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TravelPackageDetailHolder {  // Fixed class name consistency

    @NotEmpty(message = "title is required")
    private String title;

    @NotEmpty(message = "description is required")
    private String description;

    @NotEmpty(message = "map link is required")
    private String mapLink;

}
