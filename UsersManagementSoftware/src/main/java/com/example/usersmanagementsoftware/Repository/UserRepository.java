package com.example.usersmanagementsoftware.Repository;

import com.example.usersmanagementsoftware.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Using find
    User findUserById(Integer id);

    List<User> findUsersByRole(String role);

    // Using JPQL
    @Query("select u from User u where u.username = ?1 and u.password =?2")
    User checkUserAccount(String username, String password);

    @Query("select u from User u where u.email=?1")
    User giveMeUserByEmail(String email);

    @Query("select u from User u where u.age>= ?1 ")
    List<User> giveMeUsersWithSpecificAgeOrAbove(Integer age);
}