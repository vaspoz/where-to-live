package ru.vaspoz.relo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
import ru.vaspoz.relo.exceptions.ParsingDoublesException;
import ru.vaspoz.relo.external.ExternalDatasourceAPI;
import ru.vaspoz.relo.external.model.OverallRates;
import ru.vaspoz.relo.model.*;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CountriesRepository;
import ru.vaspoz.relo.repository.CountryRatesRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Autowired
    private CityRatesFullService cityRatesFullService;

    public CountryRateResponseGET getSingleCountryComparedRates_v2(
            String baseCountry,
            String baseCity,
            String countryToCompare,
            int amountOfCities) {

        List<CountryRate> resultingRates = new ArrayList<>();

        Country country = countriesRepository.findByCountry(countryToCompare);
        if (country == null) return null;
        List<City> cities = citiesRepository.findByCountryId(country.getId());

        if (amountOfCities == -1)
            amountOfCities = cities.size();

        for (int i = 0; i < amountOfCities; i++) {
            City city = cities.get(i);
            try {
                OverallRates ratesForGivenCity = cityRatesFullService.getRatesBetweenCities(
                        baseCountry,
                        baseCity,
                        country.getCountry(),
                        city.getCity()
                );
                CountryRate countryRate = new CountryRate(
                        baseCountry,
                        baseCity,
                        country.getCountry(),
                        city.getCity(),
                        ratesForGivenCity.getExpenses(),
                        ratesForGivenCity.getSalary(),
                        ratesForGivenCity.getOverall()
                );

                resultingRates.add(countryRate);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                log.error("\nCould not find\n\tCity [" + city.getCity() + "]\n\tCountry [" + country.getCountry() + "]" +
                        "\nCity will be deleted from data table CITIES\n");
                citiesRepository.delete(city);
            }
        }

        return transformEntityToResponse(countryToCompare, resultingRates);
    }


    public CountryRateResponseGET getSingleCountryComparedRates_old(
            String baseCountry,
            String baseCity,
            String countryToCompare,
            int amountOfCities) {

        List<CountryRate> resultingRates = new ArrayList<>();

        Country country = countriesRepository.findByCountry(countryToCompare);
        if (country == null) return null;
        List<City> cities = citiesRepository.findByCountryId(country.getId());

        ExternalDatasourceAPI api = ExternalDatasourceAPI.getAPI();
        if (amountOfCities == -1)
            amountOfCities = cities.size();
        for (int i = 0; i < amountOfCities; i++) {
            City city = cities.get(i);
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
                log.error("\nCould not find\n\tCity [" + city.getCity() + "]\n\tCountry [" + country.getCountry() + "]" +
                        "\nCity will be deleted from data table CITIES\n");
                citiesRepository.delete(city);
            }
        }

        return transformEntityToResponse(countryToCompare, resultingRates);
    }

    public CountryRateResponseGET getSingleCountryComparedRates(
            String baseCountry,
            String baseCity,
            String countryToCompare) {

        Double baseSalaryData = cityRatesFullService.getCityOverallSalary(baseCountry, baseCity);
        Double baseExpensesData = cityRatesFullService.getCityOverallExpenses(baseCountry, baseCity);

        Country country = countriesRepository.findByCountry(countryToCompare);
        if (country == null) return null;
        List<City> cities = citiesRepository.findByCountryId(country.getId());

        List<CountryRate> resultingRates = new ArrayList<>();

        for (City city : cities) {
            String countryName = city.getCountry().getCountry();
            String cityName = city.getCity();
            Double salary = cityRatesFullService.getCityOverallSalary(countryName, cityName);
            Double expenses = cityRatesFullService.getCityOverallExpenses(countryName, cityName);

            Double relativeExpensesValue = getRelativeValue(expenses, baseExpensesData);
            Double relativeSalaryValue = getRelativeValue(salary, baseSalaryData);
            Double relativeOverallValue = Double.sum(relativeSalaryValue, -relativeExpensesValue);

            resultingRates.add(new CountryRate(
                    baseCountry,
                    baseCity,
                    countryName,
                    cityName,
                    make2digits(relativeExpensesValue),
                    make2digits(relativeSalaryValue),
                    make2digits(relativeOverallValue)
            ));
        }

        return transformEntityToResponse(countryToCompare, resultingRates);
    }

    private Double getRelativeValue(Double firstValue, Double secondValue) {
        return 100 * (Double.sum(firstValue, -secondValue)) / secondValue;

    }

    private CountryRateResponseGET transformEntityToResponse(String countryToCompare, List<CountryRate> countryRateList) {
        CountryRateResponseGET countryRateResponse = new CountryRateResponseGET();
        countryRateResponse.setCountry(countryToCompare);
        countryRateResponse.setCityRates(new ArrayList<>());

        for (CountryRate countryRate : countryRateList) {
            countryRateResponse.getCityRates().add(new CityRate(
                    countryRate.getComparedWithCity(),
                    countryRate.getExpenses(),
                    countryRate.getSalary(),
                    countryRate.getOverall()
            ));
        }

        return countryRateResponse;
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
                .map(elt -> ((City) elt).getCity())
                .collect(Collectors.toList());
    }

    public List<String> getCountriesList() {
        Iterable<Country> countryIter = countriesRepository.findAll();
        return StreamSupport.stream(countryIter.spliterator(), false)
                .map(Country::getCountry)
                .collect(Collectors.toList());
    }

    public List<String> get5bestCountries(String baseCountry, String baseCity) {
        List<String> bestCountries = new ArrayList<>();
        bestCountries.add("AAA");
        bestCountries.add("BBB");
        bestCountries.add("CCC");
        return bestCountries;
    }

    private Double make2digits(Double d) {
        return Math.floor(d * 100) / 100;
    }

}
