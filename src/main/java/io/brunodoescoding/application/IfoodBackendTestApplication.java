package io.brunodoescoding.application;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.brunodoescoding.business.impl.TemperaturePicker;
import io.brunodoescoding.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@ComponentScan("io.brunodoescoding")
@SpringBootApplication
public class IfoodBackendTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfoodBackendTestApplication.class, args);
	}

	@Autowired
	TemperatureService temperatureService;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean(name = "temperatureCache")
	public LoadingCache<String, String> temperatureCache() {
		return CacheBuilder.newBuilder()
				.expireAfterWrite(10, TimeUnit.MINUTES)
				.maximumSize(10000)
				.build(new CacheLoader<String, String>() {
					public String load(String key) {
						return key.toLowerCase();
					}
				});
	}

	@Bean(name = "credentialsCache")
	public LoadingCache<String, String> credentialsCache() {
		return CacheBuilder.newBuilder()
				.expireAfterWrite(3600, TimeUnit.SECONDS)
				.maximumSize(1)
				.build(new CacheLoader<String, String>() {
					public String load(String key) {
						return key.toLowerCase();
					}
				});
	}

	@Bean(name = "tracksCache")
	public LoadingCache<String, String> tracksCache() {
		return CacheBuilder.newBuilder()
				.expireAfterWrite(1, TimeUnit.MINUTES)
				.maximumSize(10)
				.build(new CacheLoader<String, String>() {
					public String load(String key) {
						return key.toLowerCase();
					}
				});
	}

	@PostConstruct
	public void init() {
		TemperaturePicker.init(temperatureService);
	}

}
