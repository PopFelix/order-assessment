package ing.assessment.service.impl;

import ing.assessment.controller.dto.CreateOrderRequestDto;
import ing.assessment.db.order.Order;
import ing.assessment.db.repository.OrderRepository;
import ing.assessment.service.OrderService;
import ing.assessment.service.validator.OrderProductValidator;
import ing.assessment.util.OrderProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        orderProductValidator.validateProductStock(createOrderRequestDto.getOrderProducts());
        order.setOrderProducts(OrderProductConverter.convertToOrderProduct(createOrderRequestDto));
        order.setTimestamp(new Date());
        order.setOrderCost(0.0);
        return order;
    }
}