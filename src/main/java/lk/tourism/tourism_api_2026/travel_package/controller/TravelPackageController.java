package lk.tourism.tourism_api_2026.travel_package.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lk.tourism.tourism_api_2026.travel_package.dto.*;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackage;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackageDetail;
import lk.tourism.tourism_api_2026.travel_package.service.TravelPackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    @RolesAllowed({"ADMIN", "TOUR_GUIDE"})
    @PostMapping(value = "/travel-packages/add-travel-package", headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTravelPackage(@Valid @RequestBody CreateTravelPackageRequest rq){

        log.trace("received request : {}", rq);

        travelPackageService.createTravelPackage(rq);

    }

    @RolesAllowed({"ADMIN", "TOUR_GUIDE", "TOURIST"})
    @GetMapping(value = "/travel-packages/view-all-travel-packages-with-code", headers = "X-Api-Version=v1")
    public List<TravelPackageItemWithCode> viewAllTravelPackagesWithCode(
            @RequestParam("section-number") Integer sectionNumber) {

        log.trace("received request param, sectionNumber : {}", sectionNumber);

        if(sectionNumber < 1){
            throw new IllegalArgumentException("section number cannot be less than 1");
        }

        int page = sectionNumber - 1;
        int size = 4;

        List<TravelPackage> fetchedTravelPackageList =
                travelPackageService.getAllTravelPackages(PageRequest.of(page, size));

        List<TravelPackageItemWithCode> rs = new ArrayList<>();

        for(TravelPackage modelObjectOne : fetchedTravelPackageList){

            TravelPackageItemWithCode travelPackageItemWithCode = TravelPackageItemWithCode
                    .builder()
                    .travelPackageCode(modelObjectOne.getTravelPackageCode())
                    .name(modelObjectOne.getName())
                    .memberCount(modelObjectOne.getMemberCount())
                    .estimatedDuration(modelObjectOne.getEstimatedDuration())
                    .totalPrice(modelObjectOne.getTotalPrice())
                    .reservationAdmissionPercentage(modelObjectOne.getReservationAdmissionPercentage())
                    .travelPackageStatus(modelObjectOne.getTravelPackageStatus())
                    .visitingLocations(new ArrayList<>())
                    .build();

            List<TravelPackageDetail> fetchedTravelPackageDetailList =
                    travelPackageService.getTravelPackageDetailByTravelPackageId(modelObjectOne.getId());

            for(TravelPackageDetail modelObjectTwo : fetchedTravelPackageDetailList){

                TravelPackageDetailItemWithCode travelPackageDetailItemWithCode = TravelPackageDetailItemWithCode
                        .builder()
                        .travelPackageDetailCode(modelObjectTwo.getTravelPackageDetailCode())
                        .destinationTitle(modelObjectTwo.getDestinationTitle())
                        .destinationDescription(modelObjectTwo.getDestinationDescription())
                        .googleMapURL(modelObjectTwo.getGoogleMapURL())
                        .build();

                travelPackageItemWithCode.getVisitingLocations().add(travelPackageDetailItemWithCode);

            }

            rs.add(travelPackageItemWithCode);

        }

        return rs;

    }


    @GetMapping(value = "/public/view-all-travel-packages-without-code", headers = "X-Api-Version=v1")
    @Cacheable(cacheNames = {"travelPackageWithoutCodeCache"}, key = "'travelPackageSectionNumber:' + #sectionNumber")
    public List<TravelPackageItemWithoutCode> viewAllTravelPackagesPublic(
            @RequestParam("section-number") Integer sectionNumber) {

        log.trace("received request param, sectionNumber : {}", sectionNumber);

        if(sectionNumber < 1){
            throw new IllegalArgumentException("section number cannot be less than 1");
        }

        int page = sectionNumber - 1;
        int size = 4;

        List<TravelPackage> fetchedTravelPackageList =
                travelPackageService.getAllTravelPackages(PageRequest.of(page, size));

        List<TravelPackageItemWithoutCode> rs = new ArrayList<>();

        for(TravelPackage modelObjectOne : fetchedTravelPackageList){

            TravelPackageItemWithoutCode travelPackageItemWithoutCode = TravelPackageItemWithoutCode
                    .builder()
                    .name(modelObjectOne.getName())
                    .memberCount(modelObjectOne.getMemberCount())
                    .estimatedDuration(modelObjectOne.getEstimatedDuration())
                    .totalPrice(modelObjectOne.getTotalPrice())
                    .reservationAdmissionPercentage(modelObjectOne.getReservationAdmissionPercentage())
                    .visitingLocations(new ArrayList<>())
                    .build();

            List<TravelPackageDetail> fetchedTravelPackageDetailList =
                    travelPackageService.getTravelPackageDetailByTravelPackageId(modelObjectOne.getId());

            for(TravelPackageDetail modelObjectTwo : fetchedTravelPackageDetailList){

                TravelPackageDetailItemWithoutCode travelPackageDetailItemWithoutCode = TravelPackageDetailItemWithoutCode
                        .builder()
                        .destinationTitle(modelObjectTwo.getDestinationTitle())
                        .destinationDescription(modelObjectTwo.getDestinationDescription())
                        .googleMapURL(modelObjectTwo.getGoogleMapURL())
                        .build();

                travelPackageItemWithoutCode.getVisitingLocations().add(travelPackageDetailItemWithoutCode);

            }

            rs.add(travelPackageItemWithoutCode);

        }

        return rs;

    }

}
