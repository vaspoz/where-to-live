package ru.vaspoz.relo.repository;

import ru.vaspoz.relo.model.CountryRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBRepository extends CrudRepository<CountryRate, Long> {

    public List<CountryRate> findByBaseCountryAndBaseCityAndComparedWithCountry(String base_country, String base_city, String compared_with_country);
    public List<CountryRate> findByBaseCountryAndBaseCity(String base_country, String base_city);
}
