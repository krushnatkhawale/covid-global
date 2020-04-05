package com.practice.covid.controller;

import com.practice.covid.model.TSDeaths;
import com.practice.covid.service.CovidDataFetchService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RestController
public class CovidController {

    private final CovidDataFetchService covidDataFetchService;

    public CovidController(CovidDataFetchService covidDataFetchService) {
        this.covidDataFetchService = covidDataFetchService;
    }

    @GetMapping("/byCountry")
    public HttpEntity<List<TSDeaths>> getByCountries() {
        List<TSDeaths> covidData = covidDataFetchService.getCovidData();
        return new ResponseEntity<>(covidData, HttpStatus.OK);
    }

    @GetMapping("/byCountry/{name}")
    public HttpEntity<List<TSDeaths>> getCountry(@PathVariable String name) {
        List<TSDeaths> covidData = covidDataFetchService.getCovidData(name);
        return new ResponseEntity<>(covidData, HttpStatus.OK);
    }

}