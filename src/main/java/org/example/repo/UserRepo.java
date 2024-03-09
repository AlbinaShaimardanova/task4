package org.example.repo;

import org.example.dto.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<Users,Integer> {
    Users findByUsername(String s);
}
