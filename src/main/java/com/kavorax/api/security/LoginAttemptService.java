package com.kavorax.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginAttemptService {

    @Value("${app.security.max-login-attempts:5}")
    private int maxAttempts;

    @Value("${app.security.lock-duration-ms:900000}") // 15 minutes default
    private long lockDurationMs;

    private final Map<String, AttemptData> attemptsCache = new ConcurrentHashMap<>();

    public void loginSucceeded(String key) {
        attemptsCache.remove(key.toLowerCase());
    }

    public void loginFailed(String key) {
        String normalizedKey = key.toLowerCase();
        long now = System.currentTimeMillis();
        attemptsCache.compute(normalizedKey, (k, existing) -> {
            if (existing == null || (now - existing.lastAttemptTimestamp > lockDurationMs)) {
                return new AttemptData(1, now);
            }
            return new AttemptData(existing.attempts + 1, now);
        });
    }

    public boolean isBlocked(String key) {
        String normalizedKey = key.toLowerCase();
        AttemptData data = attemptsCache.get(normalizedKey);
        if (data == null) {
            return false;
        }

        long now = System.currentTimeMillis();
        if (now - data.lastAttemptTimestamp > lockDurationMs) {
            attemptsCache.remove(normalizedKey);
            return false;
        }

        return data.attempts >= maxAttempts;
    }

    private static class AttemptData {
        final int attempts;
        final long lastAttemptTimestamp;

        AttemptData(int attempts, long lastAttemptTimestamp) {
            this.attempts = attempts;
            this.lastAttemptTimestamp = lastAttemptTimestamp;
        }
    }
}
