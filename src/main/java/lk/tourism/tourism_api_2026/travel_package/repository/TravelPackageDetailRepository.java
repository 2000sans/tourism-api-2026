package lk.tourism.tourism_api_2026.travel_package.repository;

import lk.tourism.tourism_api_2026.travel_package.model.TravelPackageDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPackageDetailRepository extends JpaRepository<TravelPackageDetail, Long> {
    List<TravelPackageDetail> findAllByTravelPackage_Id(Long travelPackageId);
}
