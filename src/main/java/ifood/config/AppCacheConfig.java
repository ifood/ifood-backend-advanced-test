package ifood.config;

import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.TimeUnit;

@Slf4j
@EnableCaching
@Configuration
public class AppCacheConfig extends CachingConfigurerSupport {

    @Value("${cache.evictTime}")
    private Integer evictTime;

    @Override
    public CacheManager cacheManager() {
        log.info("Inicializando cache...");

        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name,
                        CacheBuilder.newBuilder()
                                .expireAfterWrite(evictTime, TimeUnit.SECONDS)
                                .build().asMap(), false);
            }
        };

        return cacheManager;
    }
}
