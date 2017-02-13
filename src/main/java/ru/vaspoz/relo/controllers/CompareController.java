package ru.vaspoz.relo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vaspoz.relo.model.CountryRate;
import ru.vaspoz.relo.repository.DBRepository;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class CompareController {

    @Autowired
    private DBRepository repository;

    @RequestMapping(value = "/compare/{baseCountry}/{baseCity}/with/{countriesToCompare}", method = RequestMethod.GET)
    public List<CountryRate> getComparedCountriesList(
            @PathVariable String baseCountry,
            @PathVariable String baseCity,
            @PathVariable String[] countriesToCompare
    ) {
        List<CountryRate> resultList = new ArrayList<>();
        for (String countryToCompare : countriesToCompare) {
            List<CountryRate> rateByOneCountry = repository.findByBaseCountryAndBaseCityAndComparedWithCountry(
                    baseCountry, baseCity, countryToCompare
            );
            resultList.addAll(rateByOneCountry);
        }

        return resultList;
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
