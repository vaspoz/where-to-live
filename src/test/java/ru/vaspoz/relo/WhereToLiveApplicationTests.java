package ru.vaspoz.relo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vaspoz.relo.model.CityRate;
import ru.vaspoz.relo.model.CountryRateResponseGET;
import ru.vaspoz.relo.services.CountryRateService;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhereToLiveApplicationTests {

    @Autowired
    private CountryRateService countryRateService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldTransformEntityToResponse() {
        String baseCountry = "Poland";
        String baseCity = "Gdansk";
        String countryToCompare = "Hong Kong";

        CityRate hongKongCityRate = new CityRate(
                "Hong Kong",
                162.87,
                225.09,
                0.0
        );
        CountryRateResponseGET expectedResponseGET = new CountryRateResponseGET(
                "Hong Kong",
                Collections.singletonList(hongKongCityRate)
        );

        CountryRateResponseGET actualResponse = countryRateService.getSingleCountryComparedRates(
                baseCountry, baseCity, countryToCompare
        );

        assertThat(actualResponse).isEqualTo(expectedResponseGET);

    }

}
