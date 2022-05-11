package com.utcn.assignment3.Mappers;

import com.utcn.assignment3.DTO.OrderDTO;
import com.utcn.assignment3.Model.Client;
import com.utcn.assignment3.Model.Food;
import com.utcn.assignment3.Model.Order;
import com.utcn.assignment3.Util.OrderStatus;
import com.utcn.assignment3.Util.Strategy.StratScam;

import java.util.ArrayList;
import java.util.List;

public final class OrderMapper {

    private OrderMapper() {}

    public static Order convertFromDTO(String extraDetails, List<Food> foods, Client client) {
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        order.setBelongsTo(client);
        order.setFoods(foods);
        order.setExtraDetails(extraDetails);

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
            dto.setExtraDetails(order.getExtraDetails());

            dtos.add(dto);
        }
        return dtos;
    }

}
