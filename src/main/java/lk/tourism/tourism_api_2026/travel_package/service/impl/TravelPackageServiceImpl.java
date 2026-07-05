package lk.tourism.tourism_api_2026.travel_package.service.impl;

import lk.tourism.tourism_api_2026.travel_package.exception.TravelPackageNotCreatedException;
import lk.tourism.tourism_api_2026.travel_package.dto.CreateTravelPackageRequest;
import lk.tourism.tourism_api_2026.travel_package.dto.TravelPackageDetailHolder;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackage;
import lk.tourism.tourism_api_2026.travel_package.model.TravelPackageDetail;
import lk.tourism.tourism_api_2026.travel_package.model.enums.TravelPackageStatus;
import lk.tourism.tourism_api_2026.travel_package.repository.TravelPackageDetailRepository;
import lk.tourism.tourism_api_2026.travel_package.repository.TravelPackageRepository;
import lk.tourism.tourism_api_2026.travel_package.service.TravelPackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class TravelPackageServiceImpl implements TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;
    private final TravelPackageDetailRepository travelPackageDetailRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void createTravelPackage(CreateTravelPackageRequest rq) {

        TravelPackage travelPackage = new TravelPackage();

        travelPackage.setTravelPackageCode( UUID.randomUUID().toString() );
        travelPackage.setName(rq.getName());
        travelPackage.setMemberCount(rq.getMemberCount());
        travelPackage.setEstimatedDuration(rq.getEstimatedDuration());
        travelPackage.setTotalPrice( BigDecimal.valueOf(rq.getTotalPrice()).setScale(2, RoundingMode.HALF_EVEN) );
        travelPackage.setTravelPackageStatus(TravelPackageStatus.ACTIVE);
        travelPackage.setTravelPackageDetailList(new ArrayList<>());


        for( TravelPackageDetailHolder object : rq.getVisitingLocations() ) {

            TravelPackageDetail travelPackageDetail = new TravelPackageDetail();

            travelPackageDetail.setTravelPackageDetailCode( UUID.randomUUID().toString() );
            travelPackageDetail.setDestinationTitle(object.getTitle());
            travelPackageDetail.setDestinationDescription(object.getDescription());
            travelPackageDetail.setGoogleMapURL(object.getMapLink());
            travelPackageDetail.setTravelPackage(travelPackage);

            travelPackage.getTravelPackageDetailList().add(travelPackageDetail);

        }

        try {
            travelPackageRepository.save(travelPackage);
        } catch (RuntimeException e) {
            throw new TravelPackageNotCreatedException(e.getMessage());
        }

    }

    @Override
    public List<TravelPackageDetail> getTravelPackageDetailByTravelPackageId(Long travelPackageId) {
        return travelPackageDetailRepository.findAllByTravelPackage_Id(travelPackageId);
    }

    @Override
    public List<TravelPackage> getAllTravelPackages(Pageable pageable) {

        Page<TravelPackage> travelPackagePage = travelPackageRepository.findAll(pageable);

        return travelPackagePage.getContent();

    }

}
