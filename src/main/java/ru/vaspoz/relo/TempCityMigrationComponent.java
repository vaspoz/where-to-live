package ru.vaspoz.relo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;
import ru.vaspoz.relo.model.City;
import ru.vaspoz.relo.model.Country;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CountriesRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class TempCityMigrationComponent {

    @Autowired
    CitiesRepository citiesRepository;

    @Autowired
    CountriesRepository countriesRepository;

    public void migrate() throws IOException {

        citiesRepository.deleteAll();
        countriesRepository.deleteAll();

        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(Paths.get("src/main/resources/db/countriesToCities.json"))
                .forEach(x -> stringBuilder.append(x));
        String jsonLine = stringBuilder.toString();

        JsonParser json = JsonParserFactory.getJsonParser();
        Map<String, Object> parcedJson = json.parseMap(jsonLine);
        Set list = parcedJson.keySet();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String country = iterator.next();
            ArrayList<String> cities = (ArrayList) parcedJson.get(country);

            Country currentCountry = new Country(country);
            countriesRepository.save(currentCountry);
            for (String city : cities) {
                City currentCity = new City(city, currentCountry);
                citiesRepository.save(currentCity);
                System.out.println("City migrated: " + currentCity.getCity());
            }
        }
    }
}
