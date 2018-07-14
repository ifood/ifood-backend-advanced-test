package ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
@EnableCaching
public class IfoodBackendAdvancedTest extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "runtime");
        SpringApplication.run(IfoodBackendAdvancedTest.class, args);
    }
}
