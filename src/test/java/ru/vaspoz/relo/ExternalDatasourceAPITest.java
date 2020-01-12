package ru.vaspoz.relo;

import org.junit.Ignore;
import org.junit.Test;
import ru.vaspoz.relo.exceptions.ParsingDoublesException;
import ru.vaspoz.relo.external.ExternalDatasourceAPI;
import ru.vaspoz.relo.external.model.OverallRates;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@Ignore
public class ExternalDatasourceAPITest {

    ExternalDatasourceAPI api = ExternalDatasourceAPI.getAPI();

    @Test
    public void shouldReturn3Doubles() throws ParsingDoublesException, IOException {

        String baseCountry = "Poland";
        String baseCity = "Gdansk";
        String compareToCountry = "Australia";
        String compareToCity = "Melbourne";

        OverallRates rates = api.getRatesBetweenCities(baseCountry, baseCity, compareToCountry, compareToCity);

        assertNotNull(rates);
    }
}
