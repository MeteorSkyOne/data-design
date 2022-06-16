package com.meteorsky.datadesign.Service;

import com.meteorsky.datadesign.Model.Department;
import com.meteorsky.datadesign.Repository.DepartmentRepository;
import com.meteorsky.datadesign.Utils.STATUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public int add(int id, String name) {
        try {
            Department department = new Department();
            department.setId(id);
            department.setName(name);
            departmentRepository.save(department);
            return STATUS.SUCCESS;
        } catch (Exception e) {
            return STATUS.FAIL;
        }
    }

    public int update(int id,String name) {
        try {
            Department department = departmentRepository.findById(id);
            if(department == null)
                return STATUS.FAIL;
            department.setName(name);
            departmentRepository.save(department);
            return STATUS.SUCCESS;
        } catch (Exception e) {
            return STATUS.FAIL;
        }
    }

    public int delete(int id) {
        try {
            Department department = departmentRepository.findById(id);
            if(department == null)
                return STATUS.FAIL;
            departmentRepository.delete(department);
            return STATUS.SUCCESS;
        } catch (Exception e) {
            return STATUS.FAIL;
        }
    }

    public List<Integer> getAllId(){
        return departmentRepository.findAllId();
    }

}
