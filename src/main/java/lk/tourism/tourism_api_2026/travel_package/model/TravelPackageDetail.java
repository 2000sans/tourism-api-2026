package lk.tourism.tourism_api_2026.travel_package.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "travel_package_details")
public class TravelPackageDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String travelPackageDetailCode;
    private String destinationTitle;
    private String destinationDescription;

    @Column(name = "google_map_url")
    private String googleMapURL;

    @ManyToOne(fetch = FetchType.LAZY)
    private TravelPackage travelPackage;

}
