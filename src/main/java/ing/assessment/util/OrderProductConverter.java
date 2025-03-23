package ing.assessment.util;

import ing.assessment.controller.dto.CreateOrderRequestDto;
import ing.assessment.controller.dto.OrderProductDto;
import ing.assessment.db.order.OrderProduct;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OrderProductConverter {

    public List<OrderProduct> convertToOrderProduct(CreateOrderRequestDto createOrderRequestDto) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        createOrderRequestDto.getOrderProducts().forEach(orderProductDto -> orderProducts.add(getOrderProductItem(orderProductDto)));
        return orderProducts;
    }

    private OrderProduct getOrderProductItem(OrderProductDto orderProductDto) {
        return OrderProduct.builder()
                .productId(orderProductDto.getProductId())
                .quantity(orderProductDto.getQuantity())
                .build();
    }
}
