package ru.plotnikov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.plotnikov.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
