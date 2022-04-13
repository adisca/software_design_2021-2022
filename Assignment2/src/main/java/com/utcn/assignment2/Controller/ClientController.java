package com.utcn.assignment2.Controller;

import com.utcn.assignment2.DTO.OrderDTO;
import com.utcn.assignment2.Mappers.OrderMapper;
import com.utcn.assignment2.Service.ClientService;
import com.utcn.assignment2.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/client/{id}")
    @ResponseBody
    public List<OrderDTO> orderHistory(@PathVariable Long id) {
        return OrderMapper.convertToDTOList(clientService.getOrders(id));
    }

    @PostMapping("/client/{id}")
    @ResponseBody
    public Map<String, Boolean> placeOrder(@PathVariable Long id, @RequestBody OrderDTO dto) {
        return Collections.singletonMap("status",
                orderService.create(OrderMapper.convertFromDTO(dto, clientService.find(id))));
    }

}
