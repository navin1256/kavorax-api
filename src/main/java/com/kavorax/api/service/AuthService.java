package com.kavorax.api.service;

import com.kavorax.api.dto.LoginRequest;
import com.kavorax.api.dto.LoginResponse;
import com.kavorax.api.entity.AdminUser;
import com.kavorax.api.repository.AdminUserRepository;
import com.kavorax.api.security.JwtService;
import com.kavorax.api.security.LoginAttemptService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final LoginAttemptService loginAttemptService;

    public AuthService(AdminUserRepository adminUserRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       LoginAttemptService loginAttemptService) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.loginAttemptService = loginAttemptService;
    }

    public LoginResponse authenticate(LoginRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        if (loginAttemptService.isBlocked(email)) {
            throw new LockedException("Account temporarily locked due to excessive failed login attempts. Please try again later.");
        }

        Optional<AdminUser> optionalUser = adminUserRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            AdminUser adminUser = optionalUser.get();
            if (passwordEncoder.matches(request.getPassword(), adminUser.getPassword())) {
                loginAttemptService.loginSucceeded(email);
                String token = jwtService.generateToken(adminUser.getEmail(), adminUser.getId(), adminUser.getRole());
                LoginResponse.UserData userData = new LoginResponse.UserData(adminUser.getId(), adminUser.getEmail(), adminUser.getRole());
                return new LoginResponse(token, userData);
            }
        }

        loginAttemptService.loginFailed(email);
        throw new BadCredentialsException("Invalid email or password");
    }
}
