package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.CountryRate;

/**
 * Created by Vasilii_Pozdeev on 12-Feb-17.
 */
@Repository
public interface DBRepository extends CrudRepository <CountryRate, Long>{

}
