package lk.tourism.tourism_api_2026.travel_package.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTravelPackageRequest {

    @NotBlank(message = "name is required")
    @Size(min = 2, max = 60, message = "name must be between 2 and 60 characters")
    private String name;

    @NotNull(message = "member count is required")
    @Positive(message = "member count must be positive")
    @Max(value = 10, message = "member count cannot exceed 10")
    private Integer memberCount;

    @NotBlank(message = "estimated duration is required")
    @Size(min = 2, max = 20, message = "estimated duration must be between 2 and 20 characters")
    @Pattern(regexp = "^\\d+\\s*(days?|weeks?|months?|years?)(,?\\s*\\d+\\s*(days?|weeks?|months?|years?))*$",
            message = "estimated duration format: e.g., '7 days', '2 weeks, 3 days'")
    private String estimatedDuration;

    @NotNull(message = "total price is required")
    @Positive(message = "total price must be positive")
    @DecimalMax(value = "100000.0", message = "total price cannot exceed 100,000")
    private Double totalPrice;

    @NotNull(message = "reservation admission percentage is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "reservation admission percentage must be greater than 0")
    @DecimalMax(value = "1.0", inclusive = false, message = "reservation admission percentage must be less than 1")
    private Float reservationAdmPercent;

    @NotEmpty(message = "at least one visiting location is required")
    @Size(max = 10, message = "visiting locations cannot exceed 10")
    @Valid
    private List<TravelPackageDetailHolder> visitingLocations;  // Fixed typo in class name

}
