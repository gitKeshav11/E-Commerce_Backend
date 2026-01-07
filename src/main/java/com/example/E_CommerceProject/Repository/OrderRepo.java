package com.example.E_CommerceProject.Repository;

import com.example.E_CommerceProject.Entity.Orders;
import com.example.E_CommerceProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders,Long> {

    @Query("Select o from Orders o Join Fetch o.user")
    List<Orders> findAllOrderWithUsers();

    List<Orders> findByUser(User user);
}
