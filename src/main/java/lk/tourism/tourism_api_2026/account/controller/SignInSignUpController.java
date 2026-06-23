package lk.tourism.tourism_api_2026.account.controller;

import lk.tourism.tourism_api_2026.account.dto.AdminSignUpRequest;
import lk.tourism.tourism_api_2026.account.dto.SignInRequest;
import lk.tourism.tourism_api_2026.account.dto.TourGuideSignUpRequest;
import lk.tourism.tourism_api_2026.account.dto.TouristSignUpRequest;
import lk.tourism.tourism_api_2026.account.security.CustomJwtUtil;
import lk.tourism.tourism_api_2026.account.service.SignInSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/public")
@RequiredArgsConstructor
public class SignInSignUpController {

    private final AuthenticationManager authenticationManager;
    private final CustomJwtUtil customJwtUtil;
    private final SignInSignUpService signInSignUpService;

    @Transactional(readOnly = true)
    @PostMapping(value = "/sign-in", headers = "X-Api-Version=v1")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody SignInRequest rq) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(rq.getUsername(), rq.getPassword()));

        log.info(authentication.toString());

        UserDetails userDetails = new User(rq.getUsername(), "", authentication.getAuthorities());
        String token = customJwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Collections.singletonMap("token", token));

    }

    @PostMapping(value = "/sign-up/admin", headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void adminSignUp(@RequestBody AdminSignUpRequest rq){

        signInSignUpService.adminSignUp(rq);

    }

    @PostMapping(value = "sign-up/tour-guide", headers = "X-Api-Version=v1")
    @ResponseStatus(HttpStatus.CREATED)
    public void tourGuideSignUp(@RequestBody TourGuideSignUpRequest rq){

        signInSignUpService.tourGuideSignUp(rq);

    }

    @PostMapping("/sign-up/tourist")
    @ResponseStatus(HttpStatus.CREATED)
    public void touristSignUp(@RequestBody TouristSignUpRequest rq){

        signInSignUpService.touristSignUp(rq);

    }

}
