package ing.assessment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@AllArgsConstructor
public class OrderProductDto {
    private Integer productId;
    private Integer quantity;
}
