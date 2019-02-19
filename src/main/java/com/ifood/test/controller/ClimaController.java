package com.ifood.test.controller;


import com.ifood.test.dto.Lugar;
import com.ifood.test.service.ClimaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/climaDatos")
@Slf4j
public class ClimaController {

    @Autowired
    private ClimaService climaService;

    @RequestMapping(value = "/lugar", method = RequestMethod.POST, consumes = "application/json")
    public Lugar obtieneDatos(@RequestBody String ciudad) {
        Lugar climaDelLugar = climaService.obtieneClimaDelLugar(ciudad);
        return climaDelLugar;
    }

}
