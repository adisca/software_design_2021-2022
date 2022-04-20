package com.utcn.assignment2.Repo;

import com.utcn.assignment2.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
