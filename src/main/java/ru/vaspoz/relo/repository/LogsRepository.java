package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.AliyahLog;
import ru.vaspoz.relo.model.City;

import java.util.List;

@Repository
public interface LogsRepository extends CrudRepository<AliyahLog, Integer> {

    List<AliyahLog> findByOrderByCreatedatetimeDesc();

}
