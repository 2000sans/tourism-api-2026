package lk.tourism.tourism_api_2026.account.service.impl;

import lk.tourism.tourism_api_2026.account.dto.AdminSignUpRequest;
import lk.tourism.tourism_api_2026.account.dto.TourGuideSignUpRequest;
import lk.tourism.tourism_api_2026.account.dto.TouristSignUpRequest;
import lk.tourism.tourism_api_2026.account.model.Account;
import lk.tourism.tourism_api_2026.account.model.enums.AccountStatus;
import lk.tourism.tourism_api_2026.account.repository.AccountRepository;
import lk.tourism.tourism_api_2026.account.service.SignInSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class SignInSignUpServiceImpl implements SignInSignUpService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void adminSignUp(AdminSignUpRequest rq) {

        log.debug(rq.getUsername());
        log.debug(rq.getPassword());

        if(accountRepository.existsByUsername(rq.getUsername())){
            throw new IllegalArgumentException("account already exist by this username");
        }

        Account adminAccount = new Account();
        adminAccount.setUsername(rq.getUsername());
        adminAccount.setPassword(passwordEncoder.encode(rq.getPassword()));
        adminAccount.setAccessLevel("ADMIN");
        adminAccount.setAccountCode( UUID.randomUUID().toString() );
        adminAccount.setAccountStatus(AccountStatus.ACTIVE);

        try {
            accountRepository.save(adminAccount);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    @Override
    public void tourGuideSignUp(TourGuideSignUpRequest rq) {

        log.debug(rq.getUsername());
        log.debug(rq.getPassword());

        if(accountRepository.existsByUsername(rq.getUsername())){
            throw new IllegalArgumentException("account already exist by this username");
        }

        Account tourGuideAccount = new Account();
        tourGuideAccount.setUsername(rq.getUsername());
        tourGuideAccount.setPassword(passwordEncoder.encode(rq.getPassword()));
        tourGuideAccount.setAccessLevel("TOUR_GUIDE");
        tourGuideAccount.setAccountCode( UUID.randomUUID().toString() );
        tourGuideAccount.setAccountStatus(AccountStatus.ACTIVE);

        try {
            accountRepository.save(tourGuideAccount);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void touristSignUp(TouristSignUpRequest rq) {

        log.debug(rq.getUsername());
        log.debug(rq.getPassword());

        if(accountRepository.existsByUsername(rq.getUsername())){
            throw new IllegalArgumentException("account already exist by this username");
        }

        Account touristAccount = new Account();
        touristAccount.setUsername(rq.getUsername());
        touristAccount.setPassword(passwordEncoder.encode(rq.getPassword()));
        touristAccount.setAccessLevel("TOURIST");
        touristAccount.setAccountCode( UUID.randomUUID().toString() );
        touristAccount.setAccountStatus(AccountStatus.ACTIVE);

        try {
            accountRepository.save(touristAccount);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
