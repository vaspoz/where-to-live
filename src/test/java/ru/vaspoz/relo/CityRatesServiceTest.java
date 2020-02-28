package ru.vaspoz.relo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vaspoz.relo.model.CityRate;
import ru.vaspoz.relo.model.CountryRateResponseGET;
import ru.vaspoz.relo.repository.CityRatesRepository;
import ru.vaspoz.relo.services.CityRatesService;
import ru.vaspoz.relo.services.CountryRateService;

import java.util.List;


@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRatesServiceTest {

    @Autowired
    private CityRatesService cityRatesService;

    @Autowired
    private CountryRateService countryRateService;

    @Autowired
    private CityRatesRepository cityRatesRepository;

    @Test
    public void shouldCompare() {

        String baseCountry = "Poland";
        String baseCity = "Gdansk";
        String compareToCountry = "France";

        System.out.println(cityRatesRepository.findByCountryAndCity(baseCountry, baseCity));

        CountryRateResponseGET response = countryRateService.getSingleCountryComparedRates(baseCountry, baseCity, compareToCountry);

        double resultOverallValue = 0.0;
        for (CityRate cityRate : response.getCityRates()) {
            resultOverallValue = Double.sum(resultOverallValue,cityRate.getOverall());
        }

        System.out.println("Overall value:\t" + (resultOverallValue/response.getCityRates().size()));

    }

}