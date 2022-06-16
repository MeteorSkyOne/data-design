package com.meteorsky.datadesign.Repository;

import com.meteorsky.datadesign.Model.Newspaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewspaperRepository extends JpaRepository<Newspaper, Long> {

    Newspaper findById(String id);

    @Query(value = "select id from newspaper", nativeQuery = true)
    List<String> findAllId();

}
