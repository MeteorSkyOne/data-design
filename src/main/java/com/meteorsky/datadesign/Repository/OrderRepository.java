package com.meteorsky.datadesign.Repository;

import com.meteorsky.datadesign.Model.Newspaper;
import com.meteorsky.datadesign.Model.Order;
import com.meteorsky.datadesign.Model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    List<Order> findByNewspaper(Newspaper newspaper);

    @Query(value = "SELECT username,count(orders.newspaper)'totalNews',sum(orders.number)'totalSub',sum(orders.price)'totalPrice' from orders,user,newspaper\n" +
            "WHERE orders.user = user.id and newspaper.id = orders.newspaper\n" +
            "GROUP BY user", nativeQuery = true)
    List<Object> summaryByUsers();

    @Query(value = "SELECT username,count(orders.newspaper)'totalNews',sum(orders.number)'totalSub',sum(orders.price)'totalPrice' from orders,user,newspaper\n" +
            "WHERE orders.user = user.id and newspaper.id = orders.newspaper and username = ?1\n" +
            "GROUP BY user", nativeQuery = true)
    List<Object> summaryByUser(String username);

    @Query(value = "SELECT newspaper.id,sum(number),sum(months),sum(orders.price) from orders,newspaper\n" +
            "WHERE\n" +
            "newspaper = newspaper.id\n" +
            "GROUP BY\n" +
            "newspaper\n", nativeQuery = true)
    List<Object> summaryByNewspaper();

    @Query(value = "SELECT ?1,newspaper.name,sum(number),sum(orders.price) from orders,user,newspaper\n" +
            "WHERE\n" +
            "orders.user = user.id and user.department = ?1 and orders.newspaper = newspaper.id", nativeQuery = true)
    Object summaryByDepartment(int department);

    @Query(value = "SELECT ?1,newspaper.name,sum(number),sum(orders.price) FROM newspaper,orders,user\n" +
            "WHERE\n" +
            "orders.newspaper = newspaper.id and orders.user = user.id and newspaper.class = ?1", nativeQuery = true)
    Object summaryByNewspaperClass(int classId);

}
