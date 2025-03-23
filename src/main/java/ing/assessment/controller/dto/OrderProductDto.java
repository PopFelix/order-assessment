package ing.assessment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderProductDto {
    private Integer productId;
    private Integer quantity;
}
