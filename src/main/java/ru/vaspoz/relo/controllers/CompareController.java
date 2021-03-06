package ru.vaspoz.relo.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vaspoz.relo.auth.AuthContext;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
import ru.vaspoz.relo.model.CountryRateResponseGET;
import ru.vaspoz.relo.services.AliyahResponseService;
import ru.vaspoz.relo.services.LoggingService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompareController {

    private static Logger log = Logger.getLogger(CompareController.class);

    @Autowired
    private AliyahResponseService service;

    @Autowired
    private LoggingService loggingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "Hello, World!";
    }

    @RequestMapping(value = "/compare/{baseCountry}/{baseCity}/with-all/{countriesToCompare}", method = RequestMethod.GET)
    public List<CountryRateResponseGET> getComparedCountriesListAll(
            @PathVariable String baseCountry,
            @PathVariable String baseCity,
            @PathVariable String[] countriesToCompare
    ) {
        List<CountryRateResponseGET> resultList = new ArrayList<>();
        for (String countryToCompare : countriesToCompare) {
            CountryRateResponseGET rateByOneCountry = service.getSingleCountryComparedRates(baseCountry, baseCity, countryToCompare, -1);
            resultList.add(rateByOneCountry);
        }

        return resultList;
    }

    @RequestMapping(value = "/compare/{baseCountry}/{baseCity}/with/{countryToCompare}", method = RequestMethod.GET)
    public CountryRateResponseGET getComparedCountriesListBySingleCountry(
            @PathVariable String baseCountry,
            @PathVariable String baseCity,
            @PathVariable String countryToCompare
    ) {

        loggingService.compareCountries(baseCountry, baseCity, countryToCompare);

        CountryRateResponseGET responseGET = service.getSingleCountryComparedRates(baseCountry, baseCity, countryToCompare, -1);
        return responseGET;
    }

    @RequestMapping(value = "/cities/by/{country}", method = RequestMethod.GET)
    public ResponseEntity<?> getCitiesByCountry(@PathVariable String country) {
        try {
            List<String> cities = service.getCitiesByCountry(country);
            return ResponseEntity.ok(cities);
        } catch (CountryNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public List<String> getCountriesList() {
        return service.getCountriesList();
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }

    @RequestMapping(value = "/pong", method = RequestMethod.GET)
    public String pong() {
        return "ping";
    }

}
