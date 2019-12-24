package application.data.repository;

import application.data.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageReprository extends JpaRepository<ProductImage,Integer> {
}
