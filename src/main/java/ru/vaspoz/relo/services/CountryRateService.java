package ru.vaspoz.relo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public void cleanCountryRecords(String country) {
        countryRatesRepository.deleteByComparedWithCountry(country);
    }

    public List<String> getCitiesByCountry(String countryName) throws CountryNotFoundException {
        Country country = countriesRepository.findByCountry(countryName);
        if (country == null) {
            throw new CountryNotFoundException();
        }
        List<City> cities = citiesRepository.findByCountryId(country.getId());
        cities.toArray();
        return Arrays.stream(cities.toArray())
                .map(elt -> ((City)elt).getCity())
                .collect(Collectors.toList());
    }
}
