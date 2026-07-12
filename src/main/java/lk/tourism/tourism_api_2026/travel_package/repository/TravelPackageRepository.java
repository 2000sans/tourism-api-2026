package lk.tourism.tourism_api_2026.travel_package.repository;

import lk.tourism.tourism_api_2026.travel_package.model.TravelPackage;
import lk.tourism.tourism_api_2026.travel_package.projections.TravelPackageItemTouristAndPublicProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {

    TravelPackage findByTravelPackageCode(String travelPackageCode);

    @Query("""
        SELECT
            tp.travelPackageCode AS travelPackageCode,
            tp.name AS travelPackageName,
            tp.memberCount AS travelPackageMemberCount,
            tp.estimatedDuration AS estimatedDuration,
            tp.totalPrice AS totalPrice
        FROM TravelPackage tp
        WHERE (:name IS NULL OR LOWER(tp.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:memberCount IS NULL OR tp.memberCount = :memberCount)
          AND (:estimatedDuration IS NULL OR LOWER(tp.estimatedDuration) LIKE LOWER(CONCAT('%', :estimatedDuration, '%')))
          AND (:totalPrice IS NULL OR tp.totalPrice = :totalPrice)
        """)
    Page<TravelPackageItemTouristAndPublicProjection> findTravelPackageItemsForTouristAndPublic(
            @Param("name") String name,
            @Param("memberCount") Integer memberCount,
            @Param("estimatedDuration") String estimatedDuration,
            @Param("totalPrice") BigDecimal totalPrice,
            Pageable pageable
    );

}
