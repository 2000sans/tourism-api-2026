package lk.tourism.tourism_api_2026.account.security;

import lk.tourism.tourism_api_2026.account.model.Account;
import lk.tourism.tourism_api_2026.account.model.enums.AccountStatus;
import lk.tourism.tourism_api_2026.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppUserDetailsProcessor implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account user = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No user found with username [" + username + "]"
                ));

        // Map AccessLevel enum to a Spring Security authority string
        String roleName = "ROLE_" + user.getAccessLevel(); // e.g. ROLE_ADMIN / ROLE_TOUR_GUIDE / ROLE_TOURIST
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        boolean enabled = user.getAccountStatus() == AccountStatus.ACTIVE;

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authority)
                .accountLocked(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(!enabled)
                .build();
    }

}
