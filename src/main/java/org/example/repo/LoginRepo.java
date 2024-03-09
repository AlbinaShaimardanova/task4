package org.example.repo;

import org.example.dto.Logins;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepo extends CrudRepository<Logins, Integer> {
}
