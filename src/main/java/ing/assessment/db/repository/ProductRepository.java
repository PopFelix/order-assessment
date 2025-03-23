package ing.assessment.db.repository;

import ing.assessment.db.product.Product;
import ing.assessment.db.product.ProductCK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, ProductCK> {
    List<Product> findByProductCk_Id(Integer id);

    @Query("select sum(quantity) from Product where productCk.id = ?1")
    Integer calculateQuantityByProductCk_Id(Integer id);
}