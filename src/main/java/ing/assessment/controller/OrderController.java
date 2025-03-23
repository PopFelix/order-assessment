package ing.assessment.controller;

import ing.assessment.controller.dto.CreateOrderRequestDto;
import ing.assessment.db.order.Order;
import ing.assessment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {return orderService.getAllOrders();}

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto){
        return orderService.createOrder(createOrderRequestDto);
    }
}
