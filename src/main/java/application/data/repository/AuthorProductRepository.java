package application.data.repository;

import application.data.model.AuthorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorProductRepository extends JpaRepository<AuthorProduct,Integer> {
}
