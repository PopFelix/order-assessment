package ing.assessment.service.impl;

import ing.assessment.controller.dto.CreateOrderRequestDto;
import ing.assessment.db.order.Order;
import ing.assessment.db.repository.OrderRepository;
import ing.assessment.service.OrderService;
import ing.assessment.service.validator.OrderProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductValidator orderProductValidator;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(CreateOrderRequestDto createOrderRequestDto) {
        Order order = new Order();
        return null;
    }
}