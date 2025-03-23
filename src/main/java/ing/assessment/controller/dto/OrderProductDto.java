package ing.assessment.controller.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {
    private Integer productId;
    private Integer quantity;
}
