package ru.plotnikov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.plotnikov.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
