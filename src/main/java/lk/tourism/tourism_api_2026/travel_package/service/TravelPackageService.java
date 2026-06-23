package lk.tourism.tourism_api_2026.travel_package.service;

import lk.tourism.tourism_api_2026.travel_package.dto.CreateTravelPackageRequest;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackage;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackageDetail;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelPackageService {

    void createTravelPackage(CreateTravelPackageRequest rq);

    List<TravelPackageDetail> getTravelPackageDetailByTravelPackageId(Long TravelPackageId);

    List<TravelPackage> getAllTravelPackages(Pageable pageable);

}
