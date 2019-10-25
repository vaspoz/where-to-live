package ru.vaspoz.relo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vaspoz.relo.model.UserInfo;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<UserInfo, Integer> {

    public List<UserInfo> findByUsername(String Username);

}
