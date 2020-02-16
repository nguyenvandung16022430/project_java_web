package application.data.repository;

import application.data.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT count(c.id) from dbo_category c")
    long getTotalCategories();


    @Query("SELECT c FROM dbo_category c " +
            "where (:categoryName IS NULL OR UPPER(c.name) LIKE CONCAT('%',UPPER(:categoryName),'%'))")
    Page<Category> getCategoryContainingCategoryName(Pageable pageable, @Param(value = "categoryName") String categoryName);

}
