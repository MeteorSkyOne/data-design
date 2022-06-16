package com.meteorsky.datadesign.Repository;

import com.meteorsky.datadesign.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findById(int id);

    @Query(value = "select id from department", nativeQuery = true)
    List<Integer> findAllId();

}
