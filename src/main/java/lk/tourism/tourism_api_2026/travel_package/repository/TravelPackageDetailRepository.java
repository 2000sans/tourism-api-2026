package lk.tourism.tourism_api_2026.travel_package.repository;

import lk.tourism.tourism_api_2026.travel_package.model.TravelPackage;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackageDetail;
import lk.tourism.tourism_api_2026.travel_package.projections.TravelPackageDetailItemTouristAndPublicProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelPackageDetailRepository extends JpaRepository<TravelPackageDetail, Long> {
    List<TravelPackageDetail> findAllByTravelPackage_Id(Long travelPackageId);

    @Query("""
        SELECT
            tpd.travelPackageDetailCode AS travelPackageDetailCode,
            tpd.destinationTitle AS destinationTitle,
            tpd.destinationDescription AS destinationDescription,
            tpd.googleMapURL AS googleMapURL
        FROM TravelPackageDetail tpd
        WHERE tpd.travelPackage = :travelPackage
        """)
    List<TravelPackageDetailItemTouristAndPublicProjection> findByTravelPackage(
            @Param("travelPackage") TravelPackage travelPackage
    );

}
