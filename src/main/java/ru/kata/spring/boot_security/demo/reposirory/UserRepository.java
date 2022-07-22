package ru.kata.spring.boot_security.demo.reposirory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   @Query("select u from User u join fetch u.roles where u.username = :username")
   User findByUsername(String username);

   @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.id")
   List<User> findAll();
}
//Интерфейс JpaRepository предоставляет набор стандартных методов (findBy, save, deleteById и др.) для работы с БД.