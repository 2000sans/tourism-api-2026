package lk.tourism.tourism_api_2026.travel_package.model;

import jakarta.persistence.*;
import lk.tourism.tourism_api_2026.travel_package.model.enums.TravelPackageStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "travel_packages")
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String travelPackageCode;
    private String name;
    private Integer memberCount;
    private String estimatedDuration;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private TravelPackageStatus travelPackageStatus;

    @OneToMany(mappedBy = "travelPackage", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval=true)
    private List<TravelPackageDetail> travelPackageDetailList;

}
