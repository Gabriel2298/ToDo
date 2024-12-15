package com.app.ToDo.repositories;

import com.app.ToDo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
