package ru.vaspoz.relo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vaspoz.relo.model.City;
import ru.vaspoz.relo.model.Country;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CountriesRepository;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MigrateCountryCityListToDB {

    @Autowired
    CitiesRepository citiesRepository;

    @Autowired
    CountriesRepository countriesRepository;

    @Before
    public void cleanUp() {
        citiesRepository.deleteAll();
        countriesRepository.deleteAll();
    }

    @Test
    public void migrate() throws IOException {
        Document mainPage = Jsoup.connect("https://www.numbeo.com/cost-of-living/").get();

        mainPage.select("select[name=\"country\"]>option")
                .forEach(elt -> {
                    String countryName = elt.text();
                    if (countryName.contains("--")) return;

                    Country country = new Country(countryName);
                    countriesRepository.save(country);
                    try {
                        Document citiesPage = Jsoup
                                .connect("https://www.numbeo.com/cost-of-living/country_result.jsp?country=" +
                                        countryName.replaceAll(" ", "+")).get();
                        citiesPage.select("select[name=\"city\"]>option")
                                .forEach(cityElt -> {
                                    String cityName = cityElt.text();
                                    if (cityName.contains("--")) return;

                                    citiesRepository.save(new City(cityName, country));
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
