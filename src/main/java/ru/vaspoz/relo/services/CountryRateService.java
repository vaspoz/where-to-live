package ru.vaspoz.relo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
import ru.vaspoz.relo.model.*;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CountriesRepository;
import ru.vaspoz.relo.repository.CountryRatesRepository;

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
    private CityRatesService cityRatesService;

    public CountryRateResponseGET getSingleCountryComparedRates(
            String baseCountry,
            String baseCity,
            String countryToCompare) {

        Double baseSalaryData = cityRatesService.getCityOverallSalary(baseCountry, baseCity);
        Double baseExpensesData = cityRatesService.getCityOverallExpenses(baseCountry, baseCity);

        Country country = countriesRepository.findByCountry(countryToCompare);
        if (country == null) return null;
        List<City> cities = citiesRepository.findByCountryId(country.getId());

        List<CountryRate> resultingRates = new ArrayList<>();

        for (City city : cities) {
            String countryName = city.getCountry().getCountry();
            String cityName = city.getCity();
            Double salary = cityRatesService.getCityOverallSalary(countryName, cityName);
            Double expenses = cityRatesService.getCityOverallExpenses(countryName, cityName);

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
