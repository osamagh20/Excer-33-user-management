package com.example.excer33.Repository;

import com.example.excer33.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {


    User findUserById(Integer id);

    User findUserByEmail(String email);

    @Query("select u from User u where u.role=?1")
    List<User> getUsersByRole(String role);

    @Query("select u from User u where u.age>=?1")
    List<User> getUsersByAge(Integer age);


}
