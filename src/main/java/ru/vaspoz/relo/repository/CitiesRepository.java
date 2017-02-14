package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.City;

import java.util.List;

@Repository
public interface CitiesRepository extends CrudRepository<City, Integer> {

    public List<City> findByCountry(String country);

}
