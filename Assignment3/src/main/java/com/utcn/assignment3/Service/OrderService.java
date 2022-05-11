package com.utcn.assignment3.Service;

import com.utcn.assignment3.Model.Food;
import com.utcn.assignment3.Model.Order;
import com.utcn.assignment3.Repo.OrderRepo;
import com.utcn.assignment3.Util.MailSender;
import com.utcn.assignment3.Util.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service class for orders
 */
@Service
public class OrderService {
    private final OrderRepo repo;

    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    /**
     * Saves to the database a new order
     *
     * @param order     The order details to be added
     * @return          True if success, false otherwise
     */
    public Boolean create(Order order) {
        if (order.getFoods().size() == 0)
            return Boolean.FALSE;
        Long restId = order.getFoods().get(0).getBelongsTo().getBelongsTo().getId();
        for (Food food : order.getFoods()) {
            if (!Objects.equals(restId, food.getBelongsTo().getBelongsTo().getId()))
                return Boolean.FALSE;
        }
        repo.save(order);

        MailSender.sendMail(order);

        return Boolean.TRUE;
    }

    /**
     * Changes the status of an order. Validations included, the normal flow can't be disturbed.
     *
     * @param id        the id of the order
     * @param status    The new status to be set
     * @return          True if success, false otherwise
     */
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
