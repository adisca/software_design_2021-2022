package com.utcn.assignment3.Repo;

import com.utcn.assignment3.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
