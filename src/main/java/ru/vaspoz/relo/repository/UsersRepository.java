package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.UserAuthenticated;
import ru.vaspoz.relo.model.UserDTO;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<UserAuthenticated, Integer> {

    public List<UserAuthenticated> findByUsername(String Username);

}
