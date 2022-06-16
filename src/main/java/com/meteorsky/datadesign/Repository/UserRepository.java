package com.meteorsky.datadesign.Repository;

import com.meteorsky.datadesign.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    List<User> findAll();

    @Transactional
    void deleteByUsername(String username);

    @Query(value = "select username from user", nativeQuery = true)
    List<String> findAllUsername();

    User findById(int id);


}

