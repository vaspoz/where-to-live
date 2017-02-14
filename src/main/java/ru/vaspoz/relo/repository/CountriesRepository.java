package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.Country;

@Repository
public interface CountriesRepository extends CrudRepository<Country, Integer> {

    public Country findByCountry(String countryName);

}
