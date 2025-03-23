package ing.assessment.service.validator;

import ing.assessment.controller.dto.OrderProductDto;
import ing.assessment.db.product.Product;
import ing.assessment.db.repository.ProductRepository;
import ing.assessment.exception.InsufficientStockException;
import ing.assessment.exception.NoProductFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderProductValidator {
    private final ProductRepository productRepository;

    public void validateProductStock(List<OrderProductDto> orderProductList) {
        for (OrderProductDto orderProduct : orderProductList) {
            Integer productId = orderProduct.getProductId();
            List<Product> foundProduct = productRepository.findByProductCk_Id(productId);
            if (foundProduct.isEmpty()) {
                throw new NoProductFoundException("No product found for id: " + productId);
            }
            Integer quantity = productRepository.calculateQuantityByProductCk_Id(productId);
            if (quantity < orderProduct.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product with id: " + productId);
            }
        }
    }
}
