package com.kavorax.api.config;

import com.kavorax.api.entity.AdminUser;
import com.kavorax.api.repository.AdminUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);

    @Value("${app.seed.admin-enabled:true}")
    private boolean seedAdminEnabled;

    @Value("${ADMIN_EMAIL:admin@kavorax.com}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD:SecureAuth_2025!}")
    private String adminPassword;

    @Bean
    public CommandLineRunner initDatabase(AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!seedAdminEnabled) {
                logger.info("Admin seeding is disabled by configuration (app.seed.admin-enabled=false).");
                return;
            }

            if (adminUserRepository.findByEmail(adminEmail).isEmpty()) {
                AdminUser adminUser = new AdminUser();
                adminUser.setEmail(adminEmail);
                adminUser.setPassword(passwordEncoder.encode(adminPassword));
                adminUser.setRole("ADMIN");
                adminUserRepository.save(adminUser);
                logger.info("Initial development administrator account provisioned: {}", adminEmail);
            } else {
                logger.info("Administrator account already exists for {}. Skipping creation.", adminEmail);
            }
        };
    }
}
