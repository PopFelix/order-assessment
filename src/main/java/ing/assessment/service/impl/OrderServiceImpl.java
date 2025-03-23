package ing.assessment.service.impl;

import ing.assessment.controller.dto.CreateOrderRequestDto;
import ing.assessment.controller.dto.OrderProductDto;
import ing.assessment.db.order.Order;
import ing.assessment.db.product.Product;
import ing.assessment.db.repository.OrderRepository;
import ing.assessment.db.repository.ProductRepository;
import ing.assessment.exception.NoProductFoundException;
import ing.assessment.service.OrderService;
import ing.assessment.service.validator.OrderProductValidator;
import ing.assessment.util.OrderProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderProductValidator orderProductValidator;
    private final ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order createOrder(CreateOrderRequestDto createOrderRequestDto) {
        Order order = new Order();
        orderProductValidator.validateProductStock(createOrderRequestDto.getOrderProducts());
        order.setOrderProducts(OrderProductConverter.convertToOrderProduct(createOrderRequestDto));
        order.setTimestamp(new Date());
        order.setOrderCost(calculateOrderCost(createOrderRequestDto));
        return order;
    }

    private Double calculateOrderCost(CreateOrderRequestDto createOrderRequestDto) {
        double orderCost = 0.0;
        List<OrderProductDto> orderProducts = createOrderRequestDto.getOrderProducts();
        for (OrderProductDto orderProduct : orderProducts) {
            Product product = productRepository.findByProductCk_Id(orderProduct.getProductId()).stream()
                    .findFirst()
                    .orElseThrow(() -> new NoProductFoundException("No product found for id: " + orderProduct.getProductId()));
            orderCost += product.getPrice() * orderProduct.getQuantity();
        }
        return orderCost;
    }
}