package ing.assessment.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDto {
    @NotEmpty(message = "list of ordered products is empty")
    private List<OrderProductDto> orderProducts;
}
