package com.meteorsky.datadesign.Service;

import com.meteorsky.datadesign.Model.NewspaperClass;
import com.meteorsky.datadesign.Model.Role;
import com.meteorsky.datadesign.Model.User;
import com.meteorsky.datadesign.Repository.NewspaperClassRepository;
import com.meteorsky.datadesign.Repository.RoleRepository;
import com.meteorsky.datadesign.Repository.UserRepository;
import com.meteorsky.datadesign.Utils.JsonResult;
import com.meteorsky.datadesign.Utils.STATUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Resource
    UserRepository userRepository;

    @Resource
    RoleRepository roleRepository;

    @Autowired
    NewspaperClassRepository newspaperClassRepository;

    @Autowired
    DepartmentService departmentService;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setRoles( roleRepository.findById( user.getId()));
        return user;
    }

    public int register(String username, String password){
        try{
            if(username.length() < 3 || username.length() > 20 || password.length() < 6 || password.length() > 20){
                return STATUS.ERROR;
            }
            if(userRepository.findByUsername(username)!=null){
                return STATUS.USERNAME_EXIST;
            }
            User user = new User();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setUsername(username);
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
            Role role = new Role();
            role.setUser(user);
            role.setName("USER");
            roleRepository.save(role);
            return STATUS.SUCCESS;
        }catch (Exception e){
            return STATUS.FAIL;
        }
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public int deleteUser(String username){
        try{
            userRepository.deleteByUsername(username);
            return STATUS.SUCCESS;
        }catch (Exception e){
            return STATUS.FAIL;
        }
    }

    public int updateUser(String username, String password, String idcard, String department, String phone, String address, String email, String realname){
        try{
            User user = userRepository.findByUsername(username);
            if(password.length() >= 6 && password.length() <= 20){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(password));
            }
            if(idcard.length() > 0){
                user.setIdcard(idcard);
            }
            if(department.length() > 0){
                user.setDepartment(departmentService.getDepartmentById(Integer.parseInt(department)));
            }
            if(phone.length() > 0){
                user.setPhone(phone);
            }
            if(address.length() > 0){
                user.setAddress(address);
            }
            if(email.length() > 0){
                user.setEmail(email);
            }
            if(realname.length() > 0){
                user.setRealname(realname);
            }
            userRepository.save(user);
            return STATUS.SUCCESS;
        }catch (Exception e){
            return STATUS.FAIL;
        }
    }

    public int promoteUser(String username){
        try{
            User user = userRepository.findByUsername(username);
            if(user == null)
                return STATUS.FAIL;
            Role role = roleRepository.findByUser(userRepository.findByUsername(username));
            if(role == null)
                return STATUS.FAIL;
            role.setUser(user);
            role.setName("ADMIN");
            roleRepository.save(role);
            return STATUS.SUCCESS;
        }catch (Exception e){
            return STATUS.FAIL;
        }
    }

    public boolean hasRole(String username, String role){
        User user = userRepository.findByUsername(username);
        if(user == null)
            return false;
        Role r = roleRepository.findByUser(user);
        if(r == null)
            return false;
        if(r.getName().equals(role))
            return true;
        return false;
    }

    public List<String> getAllUsername(){
        return userRepository.findAllUsername();
    }

}

