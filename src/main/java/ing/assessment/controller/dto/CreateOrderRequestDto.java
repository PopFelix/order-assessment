package ing.assessment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderRequestDto {
    private List<OrderProductDto> orderProducts;
}
