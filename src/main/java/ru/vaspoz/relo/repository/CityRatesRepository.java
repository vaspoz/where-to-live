package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.CityRateFull;

import java.util.List;

@Repository
public interface CityRatesRepository extends CrudRepository<CityRateFull, Integer> {

    public List<CityRateFull> findByCountryAndCity(String country, String city);

}
