package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.SingleCityDataset;

import java.util.List;

@Repository
public interface SingleCityDatasetRepository extends CrudRepository<SingleCityDataset, Integer> {

    public List<SingleCityDataset> findByCountryAndCity(String country, String city);

}
