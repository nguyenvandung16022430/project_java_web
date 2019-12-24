package application.data.repository;

import application.data.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    @Query("select count(p.id) from dbo_product_category p")
    long getTotalProducts();
}
