package ing.assessment.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {
    @NotNull(message = "productId is null")
    private Integer productId;

    @NotNull(message = "quantity is null")
    private Integer quantity;
}
