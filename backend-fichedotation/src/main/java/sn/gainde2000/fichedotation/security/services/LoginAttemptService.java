package sn.gainde2000.fichedotation.security.services;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Service
public class LoginAttemptService {
    public static final Logger LOGGER = LogManager.getLogger(LoginAttemptService.class);
    public static final Integer DURATION_TIME = 5;

    private final LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(DURATION_TIME, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    //

    public void loginSucceeded(final String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(final String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException e) {
            LOGGER.log(Level.valueOf("context"), e);
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(final String key) {
        try {
            int maxAttempt = 3;
            return attemptsCache.get(key) >= maxAttempt;
        } catch (final ExecutionException e) {
            return false;
        }
    }
}
