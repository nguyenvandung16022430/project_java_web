package application.data.repository;

import application.data.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select count(p.id) from dbo_product p")
    long getTotalProducts();

    @Query(" select p FROM  dbo_product p JOIN p.productCategories dc" +
            " where (:categoryId IS NULL OR (dc.categoryId = :categoryId))")
    Page<Product> getListProductbyCategoryOrProductNameContaining(Pageable page,@Param("categoryId") Integer categoryId);




    @Query(value = "SELECT * from dbo_product p JOIN dbo_product_category dc on p.product_id = dc.product_id" +
            " where dc.category_id = :categoryId ",nativeQuery = true)
    List<Product> getListProductbyCategoryIdContaining(@Param(value = "categoryId") Integer categoryId);

    @Query("SELECT p FROM dbo_product p " +
            "where (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
    Page<Product> getListProductbyProductNameContaining(Pageable page,@Param("productName") String productName);

}
