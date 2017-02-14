package ru.vaspoz.relo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaspoz.relo.exceptions.ParsingDoublesException;
import ru.vaspoz.relo.model.City;
import ru.vaspoz.relo.model.Country;
import ru.vaspoz.relo.model.CountryRate;
import ru.vaspoz.relo.numbeo.NumbeoAPI;
import ru.vaspoz.relo.numbeo.model.OverallRates;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CountriesRepository;
import ru.vaspoz.relo.repository.CountryRatesRepository;

import java.io.IOException;
import java.util.List;

@Service
public class CountryRateService {

    private static Logger log = Logger.getLogger(CountryRateService.class);

    @Autowired
    private CountryRatesRepository countryRatesRepository;

    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    public List<CountryRate> getCountriesComparedRates(
            String baseCountry,
            String baseCity,
            String countryToCompare) {

        List<CountryRate> resultingRates = countryRatesRepository.findByBaseCountryAndBaseCityAndComparedWithCountry(
                baseCountry,
                baseCity,
                countryToCompare);

        if (resultingRates == null || resultingRates.size() == 0) {
            Country country = countriesRepository.findByCountry(countryToCompare);
            if (country == null) return null;
            List<City> cities = citiesRepository.findByCountryId(country.getId());

            NumbeoAPI api = NumbeoAPI.getAPI();
            for (City city : cities) {
                try {
                    OverallRates ratesForGivenCity = api.getRatesBetweenCities(
                            baseCountry,
                            baseCity,
                            country.getCountry(),
                            city.getCity()
                    );
                    CountryRate fetchedFromNumbeo = new CountryRate(
                            baseCountry,
                            baseCity,
                            country.getCountry(),
                            city.getCity(),
                            ratesForGivenCity.getExpenses(),
                            ratesForGivenCity.getSalary(),
                            ratesForGivenCity.getOverall()
                    );

                    countryRatesRepository.save(fetchedFromNumbeo);
                    resultingRates.add(fetchedFromNumbeo);
                } catch (ParsingDoublesException | IOException e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                    log.error(npe.getMessage());
                    citiesRepository.delete(city);
                }
            }
        }

        return resultingRates;

    }
}
