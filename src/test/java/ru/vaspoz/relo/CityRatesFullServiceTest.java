package ru.vaspoz.relo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vaspoz.relo.exceptions.ParsingDoublesException;
import ru.vaspoz.relo.model.CityRate;
import ru.vaspoz.relo.model.CountryRateResponseGET;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CityRatesFullRepository;
import ru.vaspoz.relo.services.CityRatesFullService;
import ru.vaspoz.relo.services.CountryRateService;

import java.io.IOException;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRatesFullServiceTest {

    @Autowired
    private CountryRateService countryRateService;

    @Test
    public void shouldCompare() throws ParsingDoublesException, IOException {

        String baseCountry = "Poland";
        String baseCity = "Gdansk";
        String compareToCountry = "France";
        int amountOfCities = 1;
        double deviationValue = 0.2;


        CountryRateResponseGET expectedResults =
                countryRateService.getSingleCountryComparedRates_old(
                        baseCountry,
                        baseCity,
                        compareToCountry,
                        amountOfCities);
        CountryRateResponseGET actualResults =
                countryRateService.getSingleCountryComparedRates_v2(
                        baseCountry,
                        baseCity,
                        compareToCountry,
                        amountOfCities);

        System.out.println("Expected:\t" + expectedResults);
        System.out.println("Actual  :\t" + actualResults);

        CityRate expectedCityRates = expectedResults.getCityRates().get(0);
        CityRate actualCityRates = actualResults.getCityRates().get(0);

        assertTrue(
                approximatelyEqual(actualCityRates.getOverall(), expectedCityRates.getOverall(), deviationValue)
        );

    }

    private boolean approximatelyEqual(Double valueInTest, Double baseline, double deviation) {
        // valueInTest = 10
        // baseline = 11
        // deviation = 0.2 (20%)
        // result = valueInTest >= (1 - deviation) * baseline &&
        //          valueInTest <= (1 + deviation) * baseline
        // ie: result := [0.8*baseline ; 1.2*baseline]
        return (valueInTest >= (1 - deviation) * baseline)
                &&
                (valueInTest <= (1 + deviation) * baseline);
    }

}