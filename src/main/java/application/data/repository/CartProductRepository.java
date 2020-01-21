package application.data.repository;

import application.data.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CartProductRepository extends JpaRepository<CartProduct,Integer> {
    CartProduct findFirstCartProductByCartIdAndProductId(@Param("cartId") int cartId, @Param("productId") int productId);
}
