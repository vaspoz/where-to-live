package ru.vaspoz.relo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vaspoz.relo.model.RelativeCityRate;
import ru.vaspoz.relo.model.CountryRateResponseGET;
import ru.vaspoz.relo.services.AliyahResponseService;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhereToLiveApplicationTest {

    @Autowired
    private AliyahResponseService aliyahResponseService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldTransformEntityToResponse() {
        String baseCountry = "Poland";
        String baseCity = "Gdansk";
        String countryToCompare = "Hong Kong";

        RelativeCityRate hongKongRelativeCityRate = new RelativeCityRate(
                "Hong Kong",
                159.81,
                203.38,
                43.56
        );
        CountryRateResponseGET expectedResponseGET = new CountryRateResponseGET(
                "Hong Kong",
                Collections.singletonList(hongKongRelativeCityRate)
        );

        CountryRateResponseGET actualResponse = aliyahResponseService.getSingleCountryComparedRates(
                baseCountry, baseCity, countryToCompare, -1
        );

        assertThat(actualResponse).isEqualTo(expectedResponseGET);

    }

}
