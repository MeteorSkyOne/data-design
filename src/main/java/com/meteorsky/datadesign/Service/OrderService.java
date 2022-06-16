package com.meteorsky.datadesign.Service;

import com.meteorsky.datadesign.Model.Department;
import com.meteorsky.datadesign.Model.Newspaper;
import com.meteorsky.datadesign.Model.NewspaperClass;
import com.meteorsky.datadesign.Model.Order;
import com.meteorsky.datadesign.Repository.*;
import com.meteorsky.datadesign.Utils.STATUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewspaperRepository newspaperRepository;

    @Autowired
    private NewspaperClassRepository newspaperClassRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public int subscribeNewspaper(String user, String newspaper, int counts, int months){
        try {
            Order order = new Order();
            order.setId(String.valueOf(System.currentTimeMillis()));
            order.setUser(userRepository.findByUsername(user));
            order.setNewspaper(newspaperRepository.findById(newspaper));
            order.setNumber(counts);
            order.setMonths(months);
            order.setPrice(counts * months * order.getNewspaper().getPrice());
            orderRepository.save(order);
            return STATUS.SUCCESS;
        } catch (Exception e) {
            return STATUS.FAIL;
        }
    }

    public List<Order> queryUser(int id){
        return orderRepository.findByUser(userRepository.findById(id));
    }

    public List<Order> queryNewspaper(String newspaper){
        return orderRepository.findByNewspaper(newspaperRepository.findById(newspaper));
    }

    public List<Order> queryDepartment(int department){
        Department d = departmentRepository.findById(department);
        List<Order> orders = new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            if(order.getUser().getDepartment() == d)
                orders.add(order);
        }
        return orders;
    }

    public List<Order> queryNewspaperClass(int newspaperClass){
        NewspaperClass n = newspaperClassRepository.findById(newspaperClass);
        List<Order> orders = new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            if(order.getNewspaper().getNewspaperClass() == n)
                orders.add(order);
        }
        return orders;
    }

    public List<Order> queryAll(){
        return orderRepository.findAll();
    }

    public List<Object> summaryByUsers(){
        return orderRepository.summaryByUsers();
    }

    public List<Object> summaryByNewspaper(){
        return orderRepository.summaryByNewspaper();
    }

    public List<Object> summaryByDepartment(){
        List<Department> departments = departmentRepository.findAll();
        List<Object> result = new ArrayList<>();
        for(Department d : departments){
            result.add(orderRepository.summaryByDepartment(d.getId()));
        }
        return result;
    }

    public List<Object> summaryByNewspaperClass() {
        List<NewspaperClass> newspaperClasses = newspaperClassRepository.findAll();
        List<Object> result = new ArrayList<>();
        for (NewspaperClass n : newspaperClasses) {
            result.add(orderRepository.summaryByNewspaperClass(n.getId()));
        }
        return result;
    }

    public List<Object> summaryByUser(String username){
        return orderRepository.summaryByUser(username);
    }

}
