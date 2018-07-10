package ifood;

import ifood.utils.WiremockStarter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.core.env.AbstractEnvironment;

import javax.annotation.PreDestroy;
import java.util.Optional;

@SpringBootApplication
public class IfoodBackendAdvancedTest extends SpringBootServletInitializer {

    public static void main(String[] args) {
        setupEnvironment();
        SpringApplication.run(IfoodBackendAdvancedTest.class, args);
    }

    @PreDestroy
    public void onExit() {
        WiremockStarter.getInstance().stop();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        setupEnvironment();
        return application.sources(IfoodBackendAdvancedTest.class);
    }

    public static String currentEnv() {
        String scope = Optional.ofNullable(System.getenv("SCOPE")).orElse("development");
        if (StringUtils.containsIgnoreCase(scope, "production")) {
            scope = "production";
        }

        return scope;
    }

    private static void setupEnvironment() {
        String scope = currentEnv();
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, scope);
    }
}
