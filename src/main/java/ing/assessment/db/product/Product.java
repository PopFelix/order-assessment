package ing.assessment.db.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @EmbeddedId
    private ProductCK productCk;

    private String name;
    private Double price;
    private Integer quantity;
}