package lk.tourism.tourism_api_2026.travel_package.service;

import lk.tourism.tourism_api_2026.travel_package.dto.CreateTravelPackageRequest;
import lk.tourism.tourism_api_2026.travel_package.dto.FilterTravelPackageItemsRequest;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackage;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackageDetail;
import lk.tourism.tourism_api_2026.travel_package.projections.TravelPackageDetailItemTouristAndPublicProjection;
import lk.tourism.tourism_api_2026.travel_package.projections.TravelPackageItemTouristAndPublicProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelPackageService {

    void createTravelPackage(CreateTravelPackageRequest rq);

    List<TravelPackageDetail> getTravelPackageDetailByTravelPackageId(Long TravelPackageId);

    List<TravelPackage> getAllTravelPackages(Pageable pageable);

    Page<TravelPackageItemTouristAndPublicProjection> filterTravelPackageItemsForTouristAndPublic(FilterTravelPackageItemsRequest rq, Pageable pageable);

    List<TravelPackageDetailItemTouristAndPublicProjection> getTravelPackageDetailItemsByTravelPackageCode(String travelPackageCode);

}
