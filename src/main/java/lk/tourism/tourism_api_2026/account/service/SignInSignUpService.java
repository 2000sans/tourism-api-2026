package lk.tourism.tourism_api_2026.account.service;

import lk.tourism.tourism_api_2026.account.dto.AdminSignUpRequest;
import lk.tourism.tourism_api_2026.account.dto.TourGuideSignUpRequest;
import lk.tourism.tourism_api_2026.account.dto.TouristSignUpRequest;

public interface SignInSignUpService {

    void adminSignUp(AdminSignUpRequest rq);

    void tourGuideSignUp(TourGuideSignUpRequest rq);

    void touristSignUp(TouristSignUpRequest rq);

}
