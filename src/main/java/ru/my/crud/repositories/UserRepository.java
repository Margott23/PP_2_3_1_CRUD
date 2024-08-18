package ru.my.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.my.crud.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
