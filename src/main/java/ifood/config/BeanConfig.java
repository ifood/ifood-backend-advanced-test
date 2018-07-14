package ifood.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
@ComponentScan("ifood")
public class BeanConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    }

    @Bean
    public RestTemplate restServiceTemplate(ObjectMapper objectMapper) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().stream().filter(m -> m instanceof StringHttpMessageConverter)
                .findFirst()
                .ifPresent(messageConverter ->
                        ((StringHttpMessageConverter) messageConverter).setDefaultCharset(Charset.forName("UTF-8")));

        restTemplate.getMessageConverters().stream().filter(m -> m instanceof MappingJackson2HttpMessageConverter)
                .findFirst()
                .ifPresent(messageConverter ->
                        ((MappingJackson2HttpMessageConverter) messageConverter).setObjectMapper(objectMapper));

        return restTemplate;
    }
}
