package application.data.repository;

import application.data.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select count(p.id) from dbo_product p")
    long getTotalProducts();

    @Query(" select p FROM  dbo_product p JOIN p.productCategories dc" +
            " where (:categoryId IS NULL OR (dc.categoryId = :categoryId))")
    Page<Product> getListProductbyCategoryOrProductNameContaining(Pageable page,@Param("categoryId") Integer categoryId);

}
