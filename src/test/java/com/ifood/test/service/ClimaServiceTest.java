package com.ifood.test.service;

import com.ifood.test.TestApplication;
import com.ifood.test.dto.Place;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = TestApplication.class)
@Slf4j
public class ClimaServiceTest {
    @Autowired
    private WeatherService climaService;

    @Test
    public void obtieneClimaDelLugar() {
        final Place atizapan = climaService.getWeatherPlace("Atizapan");
        log.info(atizapan.toString());
        assertNotNull(atizapan);
    }
}
