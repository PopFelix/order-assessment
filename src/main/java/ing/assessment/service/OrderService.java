package ing.assessment.service;

import ing.assessment.controller.dto.CreateOrderRequestDto;
import ing.assessment.db.order.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order createOrder(CreateOrderRequestDto createOrderRequestDto);
}