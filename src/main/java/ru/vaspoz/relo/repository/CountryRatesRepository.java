package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.CountryRate;

import java.util.List;

@Repository
public interface CountryRatesRepository extends CrudRepository<CountryRate, Long> {

    public List<CountryRate> findByBaseCountryAndBaseCityAndComparedWithCountry(String base_country, String base_city, String compared_with_country);
    public List<CountryRate> findByBaseCountryAndBaseCity(String base_country, String base_city);
}
