package lk.tourism.tourism_api_2026.travel_package.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lk.tourism.tourism_api_2026.travel_package.dto.*;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackage;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackageDetail;
import lk.tourism.tourism_api_2026.travel_package.projections.TravelPackageDetailItemTouristAndPublicProjection;
import lk.tourism.tourism_api_2026.travel_package.projections.TravelPackageItemTouristAndPublicProjection;
import lk.tourism.tourism_api_2026.travel_package.service.TravelPackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/travel-packages")
@RequiredArgsConstructor
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    @RolesAllowed({"ADMIN", "TOUR_GUIDE"})
    @PostMapping(value = "/add-travel-package", headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTravelPackage(@Valid @RequestBody CreateTravelPackageRequest rq){

        log.trace("received request : {}", rq);

        travelPackageService.createTravelPackage(rq);

    }

    @RolesAllowed({"ADMIN", "TOUR_GUIDE", "TOURIST"})
    @GetMapping(value = "/view-all-travel-packages/admin-and-tour-guide", headers = "X-Api-Version=v1")
    public List<TravelPackageItemForAdminAndTourGuide> viewAllTravelPackagesForAdminAndTourGuide(
            @RequestParam("section-number") Integer sectionNumber) {

        log.trace("received request param, sectionNumber : {}", sectionNumber);

        if(sectionNumber < 1){
            throw new IllegalArgumentException("section number cannot be less than 1");
        }

        int page = sectionNumber - 1;
        int size = 4;

        List<TravelPackage> fetchedTravelPackageList =
                travelPackageService.getAllTravelPackages(PageRequest.of(page, size));

        List<TravelPackageItemForAdminAndTourGuide> rs = new ArrayList<>();

        for(TravelPackage modelObjectOne : fetchedTravelPackageList){

            TravelPackageItemForAdminAndTourGuide travelPackageItemForAdminAndTourGuide = TravelPackageItemForAdminAndTourGuide
                    .builder()
                    .travelPackageCode(modelObjectOne.getTravelPackageCode())
                    .name(modelObjectOne.getName())
                    .memberCount(modelObjectOne.getMemberCount())
                    .estimatedDuration(modelObjectOne.getEstimatedDuration())
                    .totalPrice(modelObjectOne.getTotalPrice())
                    .travelPackageStatus(modelObjectOne.getTravelPackageStatus())
                    .visitingLocations(new ArrayList<>())
                    .build();

            List<TravelPackageDetail> fetchedTravelPackageDetailList =
                    travelPackageService.getTravelPackageDetailByTravelPackageId(modelObjectOne.getId());

            for(TravelPackageDetail modelObjectTwo : fetchedTravelPackageDetailList){

                TravelPackageDetailItemForAdminAndTourGuide travelPackageDetailItemForAdminAndTourGuide = TravelPackageDetailItemForAdminAndTourGuide
                        .builder()
                        .travelPackageDetailCode(modelObjectTwo.getTravelPackageDetailCode())
                        .destinationTitle(modelObjectTwo.getDestinationTitle())
                        .destinationDescription(modelObjectTwo.getDestinationDescription())
                        .googleMapURL(modelObjectTwo.getGoogleMapURL())
                        .build();

                travelPackageItemForAdminAndTourGuide.getVisitingLocations().add(travelPackageDetailItemForAdminAndTourGuide);

            }

            rs.add(travelPackageItemForAdminAndTourGuide);

        }

        return rs;

    }

    @RolesAllowed({"TOURIST"})
    @GetMapping(value = "/view-all-travel-packages/tourist", headers = "X-Api-Version=v1")
    @Cacheable(cacheNames = {"travelPackageCachePublicAndTourist"}, key = "'travelPackageSectionNumber:' + #sectionNumber")
    public List<TravelPackageItemForPublicAndTourist> viewAllTravelPackagesPublic(
            @RequestParam("section-number") Integer sectionNumber) {

        log.trace("received request param, sectionNumber : {}", sectionNumber);

        if(sectionNumber < 1){
            throw new IllegalArgumentException("section number cannot be less than 1");
        }

        int page = sectionNumber - 1;
        int size = 4;

        List<TravelPackage> fetchedTravelPackageList =
                travelPackageService.getAllTravelPackages(PageRequest.of(page, size));

        List<TravelPackageItemForPublicAndTourist> rs = new ArrayList<>();

        for(TravelPackage modelObjectOne : fetchedTravelPackageList){

            TravelPackageItemForPublicAndTourist travelPackageItemForPublicAndTourist = TravelPackageItemForPublicAndTourist
                    .builder()
                    .travelPackageCode(modelObjectOne.getTravelPackageCode())
                    .name(modelObjectOne.getName())
                    .memberCount(modelObjectOne.getMemberCount())
                    .estimatedDuration(modelObjectOne.getEstimatedDuration())
                    .totalPrice(modelObjectOne.getTotalPrice())
                    .visitingLocations(new ArrayList<>())
                    .build();

            List<TravelPackageDetail> fetchedTravelPackageDetailList =
                    travelPackageService.getTravelPackageDetailByTravelPackageId(modelObjectOne.getId());

            for(TravelPackageDetail modelObjectTwo : fetchedTravelPackageDetailList){

                TravelPackageDetailItemForPublicAndTourist travelPackageDetailItemForPublicAndTourist = TravelPackageDetailItemForPublicAndTourist
                        .builder()
                        .travelPackageDetailCode(modelObjectTwo.getTravelPackageDetailCode())
                        .destinationTitle(modelObjectTwo.getDestinationTitle())
                        .destinationDescription(modelObjectTwo.getDestinationDescription())
                        .googleMapURL(modelObjectTwo.getGoogleMapURL())
                        .build();

                travelPackageItemForPublicAndTourist.getVisitingLocations().add(travelPackageDetailItemForPublicAndTourist);

            }

            rs.add(travelPackageItemForPublicAndTourist);

        }

        return rs;

    }

    @GetMapping(value = "/public/view-all-travel-packages", headers = "X-Api-Version=v1")
    @Cacheable(cacheNames = {"travelPackageCachePublicAndTourist"}, key = "'travelPackageSectionNumber:' + #sectionNumber")
    public List<TravelPackageItemForPublicAndTourist> viewAllTravelPackagesForTourist(
            @RequestParam("section-number") Integer sectionNumber) {

        log.trace("received request param, sectionNumber : {}", sectionNumber);

        if(sectionNumber < 1){
            throw new IllegalArgumentException("section number cannot be less than 1");
        }

        int page = sectionNumber - 1;
        int size = 4;

        List<TravelPackage> fetchedTravelPackageList =
                travelPackageService.getAllTravelPackages(PageRequest.of(page, size));

        List<TravelPackageItemForPublicAndTourist> rs = new ArrayList<>();

        for(TravelPackage modelObjectOne : fetchedTravelPackageList){

            TravelPackageItemForPublicAndTourist travelPackageItemForPublicAndTourist = TravelPackageItemForPublicAndTourist
                    .builder()
                    .travelPackageCode(modelObjectOne.getTravelPackageCode())
                    .name(modelObjectOne.getName())
                    .memberCount(modelObjectOne.getMemberCount())
                    .estimatedDuration(modelObjectOne.getEstimatedDuration())
                    .totalPrice(modelObjectOne.getTotalPrice())
                    .visitingLocations(new ArrayList<>())
                    .build();

            List<TravelPackageDetail> fetchedTravelPackageDetailList =
                    travelPackageService.getTravelPackageDetailByTravelPackageId(modelObjectOne.getId());

            for(TravelPackageDetail modelObjectTwo : fetchedTravelPackageDetailList){

                TravelPackageDetailItemForPublicAndTourist travelPackageDetailItemForPublicAndTourist = TravelPackageDetailItemForPublicAndTourist
                        .builder()
                        .travelPackageDetailCode(modelObjectTwo.getTravelPackageDetailCode())
                        .destinationTitle(modelObjectTwo.getDestinationTitle())
                        .destinationDescription(modelObjectTwo.getDestinationDescription())
                        .googleMapURL(modelObjectTwo.getGoogleMapURL())
                        .build();

                travelPackageItemForPublicAndTourist.getVisitingLocations().add(travelPackageDetailItemForPublicAndTourist);

            }

            rs.add(travelPackageItemForPublicAndTourist);

        }

        return rs;

    }

    @RolesAllowed({"TOURIST"})
    @PostMapping(value = "/filter-travel-package-items/tourist", headers = "X-Api-Version=v1")
    public Page<TravelPackageItemTouristAndPublicProjection> filterTravelPackagesTourist(@RequestBody FilterTravelPackageItemsRequest rq) {

        log.trace("received request : {}", rq);

        if(rq.getSectionNumber() < 1){
            throw new IllegalArgumentException("section number cannot be less than 1");
        }

        int page = rq.getSectionNumber() - 1;
        int size = rq.getSectionSize();

        Pageable pageable = PageRequest.of(page, size);

        return travelPackageService.filterTravelPackageItemsForTouristAndPublic(rq, pageable);

    }

    @RolesAllowed({"TOURIST"})
    @GetMapping(value = "/view-travel-package-detail-items/tourist", headers = "X-Api-Version=v1")
    public List<TravelPackageDetailItemTouristAndPublicProjection> viewTravelPackageDetailItemsTourist(@RequestParam("travel-package-code") String travelPackageCode){

        log.trace("received request param, travelPackageCode : {}", travelPackageCode);

        return travelPackageService.getTravelPackageDetailItemsByTravelPackageCode(travelPackageCode);

    }


    @PostMapping(value = "/public/filter-travel-package-items", headers = "X-Api-Version=v1")
    public Page<TravelPackageItemTouristAndPublicProjection> filterTravelPackagesPublic(@RequestBody FilterTravelPackageItemsRequest rq) {

        log.trace("received request : {}", rq);

        if(rq.getSectionNumber() < 1){
            throw new IllegalArgumentException("section number cannot be less than 1");
        }

        int page = rq.getSectionNumber() - 1;
        int size = rq.getSectionSize();

        Pageable pageable = PageRequest.of(page, size);

        return travelPackageService.filterTravelPackageItemsForTouristAndPublic(rq, pageable);

    }

    @GetMapping(value = "/public/view-travel-package-detail-items", headers = "X-Api-Version=v1")
    public List<TravelPackageDetailItemTouristAndPublicProjection> viewTravelPackageDetailItemsPublic(@RequestParam("travel-package-code") String travelPackageCode){

        log.trace("received request param, travelPackageCode : {}", travelPackageCode);

        return travelPackageService.getTravelPackageDetailItemsByTravelPackageCode(travelPackageCode);

    }

}
