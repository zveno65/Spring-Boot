package ru.plotnikov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.plotnikov.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
