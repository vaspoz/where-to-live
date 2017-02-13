package ru.vaspoz.relo;

import org.junit.Test;
import ru.vaspoz.relo.numbeo.NumbeoAPI;
import ru.vaspoz.relo.numbeo.model.OverallRates;

import static org.junit.Assert.assertNotNull;

public class NumbeoAPITest {

    NumbeoAPI api = NumbeoAPI.getAPI();

    @Test
    public void shouldReturn3Doubles() {

        String baseCountry = "Poland";
        String baseCity = "Gdansk";
        String compareToCountry = "China";
        String compareToCity = "Hohhot";

        OverallRates rates = api.getRatesBetweenCities(baseCountry, baseCity, compareToCountry, compareToCity);

        assertNotNull(rates);
    }
}
