package ing.assessment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class CreateOrderRequestDto {
    private List<OrderProductDto> orderProducts;
}
