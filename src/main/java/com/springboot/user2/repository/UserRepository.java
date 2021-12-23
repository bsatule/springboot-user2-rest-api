package com.springboot.user2.repository;

import com.springboot.user2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    public List<User> findByFirstNameOrLastNameOrPin(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("pin") String pin);

    List<User> findByOrderByDateOfJoiningAsc();

    List<User> findByOrderByDateOfBirthAsc();

}
