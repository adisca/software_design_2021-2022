package com.utcn.assignment2.Mappers;

import com.utcn.assignment2.DTO.OrderDTO;
import com.utcn.assignment2.Model.Client;
import com.utcn.assignment2.Model.Food;
import com.utcn.assignment2.Model.Order;
import com.utcn.assignment2.Util.OrderStatus;
import com.utcn.assignment2.Util.Strategy.StratFair;
import com.utcn.assignment2.Util.Strategy.StratScam;

import java.util.ArrayList;
import java.util.List;

public final class OrderMapper {

    private OrderMapper() {}

    public static Order convertFromDTO(OrderDTO dto, List<Food> foods, Client client) {
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        order.setBelongsTo(client);
        order.setFoods(foods);

        return order;
    }

    public static List<OrderDTO> convertToDTOList(List<Order> orders) {
        List<OrderDTO> dtos = new ArrayList<>();
        FoodMapper foodMapper = new FoodMapper(new StratScam());
        for (Order order : orders) {
            OrderDTO dto = new OrderDTO();
            dto.setId(order.getId());
            dto.setStatus(order.getStatus().name());
            dto.setFoods(foodMapper.convertToDTOList(order.getFoods()));
            dto.setBelongsTo(ClientMapper.getInstance().convertToDTO(order.getBelongsTo()));

            dtos.add(dto);
        }
        return dtos;
    }

}
