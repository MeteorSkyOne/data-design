package com.meteorsky.datadesign.Repository;

import com.meteorsky.datadesign.Model.NewspaperClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewspaperClassRepository extends JpaRepository<NewspaperClass, Long> {

    NewspaperClass findById(int id);

    @Query(value = "select id from newspaper_class", nativeQuery = true)
    List<Integer> findAllId();

}
