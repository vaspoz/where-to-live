package ru.vaspoz.relo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
import ru.vaspoz.relo.model.CountryRate;
import ru.vaspoz.relo.services.CountryRateService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompareController {

    @Autowired
    private CountryRateService service;

    @RequestMapping(value = "/compare/{baseCountry}/{baseCity}/with/{countriesToCompare}", method = RequestMethod.GET)
    public List<CountryRate> getComparedCountriesList(
            @PathVariable String baseCountry,
            @PathVariable String baseCity,
            @PathVariable String[] countriesToCompare
    ) {
        List<CountryRate> resultList = new ArrayList<>();
        for (String countryToCompare : countriesToCompare) {
            List<CountryRate> rateByOneCountry = service.getCountriesComparedRates(baseCountry, baseCity, countryToCompare);
            resultList.addAll(rateByOneCountry);
        }

        return resultList;
    }

    @RequestMapping(value = "/cleaning-room/{countries}", method = RequestMethod.GET)
    public void cleanCountryRecords(@PathVariable String[] countries) {
        for (String country : countries) {
            service.cleanCountryRecords(country);
        }
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

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }

    @RequestMapping(value = "/pong", method = RequestMethod.GET)
    public String pong() {
        return "ping";
    }

}
