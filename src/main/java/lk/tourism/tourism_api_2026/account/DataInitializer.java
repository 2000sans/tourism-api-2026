package lk.tourism.tourism_api_2026.account;

import lk.tourism.tourism_api_2026.account.model.Account;
import lk.tourism.tourism_api_2026.account.model.enums.AccountStatus;
import lk.tourism.tourism_api_2026.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Only create if not exists
        if (accountRepository.findByUsername("admin200").isEmpty()) {
            Account adminAccount = new Account();
            adminAccount.setUsername("admin200");
            adminAccount.setPassword(passwordEncoder.encode("admin200p"));
            adminAccount.setAccessLevel("ADMIN");
            adminAccount.setAccountCode( UUID.randomUUID().toString() );
            adminAccount.setAccountStatus(AccountStatus.ACTIVE);

            accountRepository.save(adminAccount);
        }

        if (accountRepository.findByUsername("tour_guide400").isEmpty()) {
            Account tourGuideAccount = new Account();
            tourGuideAccount.setUsername("tour_guide400");
            tourGuideAccount.setPassword(passwordEncoder.encode("tour_guide400p"));
            tourGuideAccount.setAccessLevel("TOUR_GUIDE");
            tourGuideAccount.setAccountCode( UUID.randomUUID().toString() );
            tourGuideAccount.setAccountStatus(AccountStatus.ACTIVE);

            accountRepository.save(tourGuideAccount);
        }

        if (accountRepository.findByUsername("tourist800").isEmpty()) {
            Account touristAccount = new Account();
            touristAccount.setUsername("tourist800");
            touristAccount.setPassword(passwordEncoder.encode("tourist800p"));
            touristAccount.setAccessLevel("TOURIST");
            touristAccount.setAccountCode( UUID.randomUUID().toString() );
            touristAccount.setAccountStatus(AccountStatus.ACTIVE);

            accountRepository.save(touristAccount);
        }

    }

}

