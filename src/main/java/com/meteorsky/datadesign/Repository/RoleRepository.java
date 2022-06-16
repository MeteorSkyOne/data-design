package com.meteorsky.datadesign.Repository;

import com.meteorsky.datadesign.Model.Role;
import com.meteorsky.datadesign.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    List<Role> findById(Integer id);

    Role findByUser(User user);

}
