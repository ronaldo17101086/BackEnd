package com.user.users.repository;

import com.user.users.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
    @Modifying
    @Query(value = "INSERT INTO user (username, email, password, status) VALUES (?, ?, ?, 'Active')", nativeQuery = true)
    void insertUser(String username, String email, String password);

    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<user> findSelectedColumns();

    @Query(value = "SELECT email, password, username FROM user", nativeQuery = true)
    List<Object[]> threecolumns();

    @Query(value = "SELECT id, email, password, username, status FROM user WHERE email = :email", nativeQuery = true)
    List<user>
    findByEmail(@Param("email") String email);


    @Query(value = "SELECT id, email, password, username, status FROM user WHERE username = :username", nativeQuery = true)
    List<user>
    usersByUsername(@Param("username") String username);


    @Query(value = "SELECT id, email, password, username, status FROM user WHERE email = :email or username = :username", nativeQuery = true)
    List<user>
    usersByEmailAndUsername(@Param("email") String email, @Param("username") String username);


}


