package ing.assessment.db.product;

import ing.assessment.model.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductCK implements Serializable {
    private static final long serialVersionUID = 5487750997378667456L;
    private Integer id;
    private Location location;
}