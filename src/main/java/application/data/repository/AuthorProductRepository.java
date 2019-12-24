package application.data.repository;

import application.data.model.AuthorProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorProductRepository extends JpaRepository<AuthorProduct,Integer> {
}
