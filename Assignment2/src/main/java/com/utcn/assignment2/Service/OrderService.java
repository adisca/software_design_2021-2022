package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Order;
import com.utcn.assignment2.Repo.OrderRepo;
import com.utcn.assignment2.Util.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public Boolean create(Order order) {
        repo.save(order);
        return Boolean.TRUE;
    }

    public Boolean accept(Long id) {
        Order order = repo.getById(id);
        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.ACCEPTED);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean decline(Long id) {
        Order order = repo.getById(id);
        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.DECLINED);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
