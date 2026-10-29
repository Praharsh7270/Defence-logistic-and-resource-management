package com.defence.logistics.config;

import com.defence.logistics.auth.entity.AppUser;
import com.defence.logistics.auth.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BootstrapAdmin {
    private static final Logger log = LoggerFactory.getLogger(BootstrapAdmin.class);

    @Bean
    ApplicationRunner createInitialAdmin(AppUserRepository users, PasswordEncoder encoder,
            @Value("${DEFENCE_ADMIN_USERNAME:admin}") String username,
            @Value("${DEFENCE_ADMIN_PASSWORD:adminpassword123}") String password,
            @Value("${DEFENCE_ADMIN_EMAIL:admin@defence.gov}") String email,
            @Value("${DEFENCE_ADMIN_FULL_NAME:System Administrator}") String fullName) {
        return args -> {
            if (username == null || username.isBlank() || password == null || password.isBlank()) {
                log.warn("[BootstrapAdmin] Admin credentials are empty, skipping.");
                return;
            }
            String adminUser = username.trim();
            users.findByUsernameIgnoreCase(adminUser).ifPresentOrElse(
                existing -> {
                    existing.setRole("ADMIN");
                    existing.setEnabled(true);
                    existing.setPasswordHash(encoder.encode(password.trim()));
                    users.save(existing);
                    log.info("[BootstrapAdmin] Existing user '{}' promoted to ADMIN with configured password.", adminUser);
                },
                () -> {
                    String adminEmail = (email == null || email.isBlank()) ? "admin@defence.gov" : email.trim().toLowerCase();
                    users.save(new AppUser(fullName, adminUser, adminEmail, encoder.encode(password.trim()), "ADMIN"));
                    log.info("[BootstrapAdmin] Created initial ADMIN user '{}'.", adminUser);
                }
            );
        };
    }
}
