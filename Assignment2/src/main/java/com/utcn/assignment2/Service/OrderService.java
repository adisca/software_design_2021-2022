package com.utcn.assignment2.Service;

import com.utcn.assignment2.Model.Food;
import com.utcn.assignment2.Model.Order;
import com.utcn.assignment2.Repo.OrderRepo;
import com.utcn.assignment2.Util.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderService {
    private final OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public Boolean create(Order order) {
        if (order.getFoods().size() == 0)
            return Boolean.FALSE;
        Long restId = order.getFoods().get(0).getBelongsTo().getBelongsTo().getId();
        for (Food food : order.getFoods()) {
            if (!Objects.equals(restId, food.getBelongsTo().getBelongsTo().getId()))
                return Boolean.FALSE;
        }
        repo.save(order);
        return Boolean.TRUE;
    }

    public Boolean changeStatus(Long id, String status) {
        OrderStatus enumStatus = OrderStatus.valueOf(status);
        Order order = repo.getById(id);
        switch (enumStatus) {
            case PENDING -> {
                return Boolean.FALSE;
            }
            case ACCEPTED, DECLINED -> {
                if (order.getStatus() != OrderStatus.PENDING) {
                    return Boolean.FALSE;
                }
            }
            case IN_MAKING -> {
                if (order.getStatus() != OrderStatus.ACCEPTED) {
                    return Boolean.FALSE;
                }
            }
            case IN_TRANSIT -> {
                if (order.getStatus() != OrderStatus.ACCEPTED && order.getStatus() != OrderStatus.IN_MAKING) {
                    return Boolean.FALSE;
                }
            }
            case DELIVERED -> {
                if (order.getStatus() != OrderStatus.ACCEPTED && order.getStatus() != OrderStatus.IN_MAKING &&
                        order.getStatus() != OrderStatus.IN_TRANSIT) {
                    return Boolean.FALSE;
                }
            }
        }
        order.setStatus(enumStatus);
        repo.save(order);
        return Boolean.TRUE;
    }
}
