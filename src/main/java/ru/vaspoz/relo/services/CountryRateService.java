package ru.vaspoz.relo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vaspoz.relo.model.CountryRate;
import ru.vaspoz.relo.repository.CountryRatesRepository;

import java.util.List;

@Service
public class CountryRateService {

    @Autowired
    private CountryRatesRepository repository;

    public List<CountryRate> getCountriesComparedRates(
            String baseCountry,
            String baseCity,
            String countryToCompare) {

        List<CountryRate> ratesFromDB = repository.findByBaseCountryAndBaseCityAndComparedWithCountry(
                baseCountry,
                baseCity,
                countryToCompare);

        if (ratesFromDB == null || ratesFromDB.size() == 0) {

        }
        return ratesFromDB;

    }
}
