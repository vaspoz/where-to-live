package ru.vaspoz.relo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vaspoz.relo.model.CountryRate;
import ru.vaspoz.relo.repository.CountryRatesRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class's purpose is for additional migration data from existing json to DB.
 * Run it only in case you have json data to import.
 * Accept format:
 * {
 *     @country_name: [
 *          {
 *              name: @city_name,
 *              expenses: @city_expenses,
 *              salary: @city_salary,
 *              overall: @city_overall
 *          },
 *     ...next city...
 *     ],
 *     ...next country...
 * }
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class MigrateExistingData {

    @Autowired
    CountryRatesRepository repository;

    @Test
    public void migrate() throws IOException {

        String baseCountry = "Poland";
        String baseCity = "Gdansk";

        //Make one line from whole json file
        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(Paths.get("src\\main\\resources\\db\\data.json"))
                .forEach(x -> stringBuilder.append(x));
        String jsonLine = stringBuilder.toString().replaceAll(" ", "");

        JsonParser json = JsonParserFactory.getJsonParser();
        Map<String, Object> parcedJson = json.parseMap(jsonLine);
        Set list = parcedJson.keySet();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String country = iterator.next();
            ArrayList<Map<String, Object>> cities = (ArrayList) parcedJson.get(country);

            for (Map<String, Object> city : cities) {
                String name = city.get("name").toString();
                Double expenses = safeCast(city.get("expenses"));
                Double salary = safeCast(city.get("salary"));
                Double overall = safeCast(city.get("overall"));

                CountryRate countryRate = new CountryRate(
                        baseCountry,
                        baseCity,
                        country,
                        name,
                        expenses,
                        salary,
                        overall
                );
                System.out.println(countryRate);

                repository.save(countryRate);
            }

        }

    }

    private Double safeCast(Object value) {
        Double d;
        try {
            d = Double.parseDouble(value.toString());
        } catch (ClassCastException cce) {
            d = (Double)value;
        }
        return d;

    }

}
