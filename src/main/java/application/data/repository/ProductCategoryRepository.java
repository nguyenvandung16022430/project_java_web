package application.data.repository;

import application.data.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    @Query("select count(p.id) from dbo_product_category p")
    long getTotalProducts();

    @Query(value = "SELECT category_id from dbo_product_category p"+"   WHERE p.product_id = :productId limit 1",nativeQuery = true)
    Integer getcategoryIdByProductid(@Param(value = "productId") Integer productId);
}
