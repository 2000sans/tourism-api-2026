package lk.tourism.tourism_api_2026.account.repository;

import lk.tourism.tourism_api_2026.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);
}
