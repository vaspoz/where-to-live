package ru.vaspoz.relo.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaspoz.relo.exceptions.CountryNotFoundException;
import ru.vaspoz.relo.model.OverallRates;
import ru.vaspoz.relo.model.*;
import ru.vaspoz.relo.repository.CitiesRepository;
import ru.vaspoz.relo.repository.CountriesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class AliyahResponseService {

    private static Logger log = Logger.getLogger(AliyahResponseService.class);

    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    @Autowired
    private GoddamnGoodComparisonService goddamnGoodComparisonService;

    public CountryRateResponseGET getSingleCountryComparedRates(
            String baseCountry,
            String baseCity,
            String countryToCompare,
            int amountOfCities) {

        List<TwoCitiesComparisonData> resultingRateList = new ArrayList<>();

        Country country = countriesRepository.findByCountry(countryToCompare);
        if (country == null) return null;
        List<City> cities = citiesRepository.findByCountryId(country.getId());

        if (amountOfCities == -1)
            amountOfCities = cities.size();

        for (int i = 0; i < amountOfCities; i++) {
            City city = cities.get(i);
            try {
                OverallRates ratesForGivenCity = goddamnGoodComparisonService.getRatesBetweenCities(
                        baseCountry,
                        baseCity,
                        country.getCountry(),
                        city.getCity()
                );
                TwoCitiesComparisonData twoCitiesComparisonData = new TwoCitiesComparisonData(
                        baseCountry,
                        baseCity,
                        country.getCountry(),
                        city.getCity(),
                        ratesForGivenCity.getExpenses(),
                        ratesForGivenCity.getSalary(),
                        ratesForGivenCity.getOverall()
                );

                resultingRateList.add(twoCitiesComparisonData);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                log.error("\nCould not find\n\tCity [" + city.getCity() + "]\n\tCountry [" + country.getCountry() + "]" +
                        "\nCity will be deleted from data table CITIES\n");
                citiesRepository.delete(city);
            }
        }

        return transformEntityToResponse(countryToCompare, resultingRateList);
    }


    private CountryRateResponseGET transformEntityToResponse(String countryToCompare, List<TwoCitiesComparisonData> citiesComparisonDataList) {
        CountryRateResponseGET countryRateResponse = new CountryRateResponseGET();
        countryRateResponse.setCountry(countryToCompare);
        countryRateResponse.setRelativeCityRateList(new ArrayList<>());

        for (TwoCitiesComparisonData countryRate : citiesComparisonDataList) {
            countryRateResponse.addRelativeCityRate(new RelativeCityRate(
                    countryRate.getComparedWithCity(),
                    countryRate.getExpenses(),
                    countryRate.getSalary(),
                    countryRate.getOverall()
            ));
        }

        return countryRateResponse;
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

}