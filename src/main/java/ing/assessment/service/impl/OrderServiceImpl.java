package ing.assessment.service.impl;

import ing.assessment.controller.dto.CreateOrderRequestDto;
import ing.assessment.controller.dto.OrderProductDto;
import ing.assessment.db.order.Order;
import ing.assessment.db.product.Product;
import ing.assessment.db.repository.OrderRepository;
import ing.assessment.db.repository.ProductRepository;
import ing.assessment.model.Location;
import ing.assessment.service.OrderService;
import ing.assessment.service.validator.OrderProductValidator;
import ing.assessment.util.OrderCalculatorUtil;
import ing.assessment.util.OrderProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        calculateCostDelivery(createOrderRequestDto, order);
        return orderRepository.save(order);
    }

    private void calculateCostDelivery(CreateOrderRequestDto createOrderRequestDto, Order order) {
        double orderCost = 0.0;
        Map<Location, Integer> locationCount = new HashMap<>();
        List<OrderProductDto> orderProducts = createOrderRequestDto.getOrderProducts();
        for (OrderProductDto orderProduct : orderProducts) {
            List<Product> products = productRepository.findByProductCk_Id(orderProduct.getProductId());
            orderCost += calculateOrderCostForProduct(locationCount, orderProduct, products);
        }
        order.setOrderCost(orderCost);
        order.setDeliveryTime(locationCount.size() * 2);
        OrderCalculatorUtil.applyDiscount(order);
    }

    private double calculateOrderCostForProduct(Map<Location, Integer> locationCount, OrderProductDto orderProduct, List<Product> products) {
        double orderCost = 0.0;
        Integer usedQuantity = orderProduct.getQuantity();
        for (var product : products) { // var keyword Java 17 feature
            if (product.getQuantity() <= usedQuantity) {
                orderCost += product.getPrice() * product.getQuantity();
                usedQuantity -= product.getQuantity();
                locationCount.put(product.getProductCk().getLocation(), locationCount.getOrDefault(product.getProductCk().getLocation(), 0) + 1);
                productRepository.deleteProductByProductCk(product.getProductCk());
            } else {
                product.setQuantity(product.getQuantity() - usedQuantity);
                productRepository.saveAndFlush(product);
                locationCount.put(product.getProductCk().getLocation(), locationCount.getOrDefault(product.getProductCk().getLocation(), 0) + 1);
                orderCost += product.getPrice() * usedQuantity;
                break;
            }
        }
        return orderCost;
    }
}