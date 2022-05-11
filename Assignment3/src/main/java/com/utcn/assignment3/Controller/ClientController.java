package com.utcn.assignment3.Controller;

import com.utcn.assignment3.DTO.OrderDTO;
import com.utcn.assignment3.Mappers.FoodMapper;
import com.utcn.assignment3.Mappers.OrderMapper;
import com.utcn.assignment3.Service.ClientService;
import com.utcn.assignment3.Service.FoodService;
import com.utcn.assignment3.Service.OrderService;
import com.utcn.assignment3.Util.Strategy.StratScam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ClientController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private FoodService foodService;

    @GetMapping("/client/{id}")
    @ResponseBody
    public List<OrderDTO> orderHistory(@PathVariable Long id) {
        return OrderMapper.convertToDTOList(clientService.getOrders(id));
    }

    @PostMapping("/client/{id}")
    @ResponseBody
    public Map<String, Boolean> placeOrder(@PathVariable Long id, @RequestBody OrderDTO dto) {
        FoodMapper foodMapper = new FoodMapper(new StratScam());
        return Collections.singletonMap("status",
                orderService.create(OrderMapper.convertFromDTO(dto.getExtraDetails(),
                        foodService.findCorespondent(foodMapper.convertFromDTOList(dto.getFoods())),
                        clientService.find(id))));
    }

}
