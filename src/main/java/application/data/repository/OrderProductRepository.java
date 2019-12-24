package application.data.repository;

import application.data.model.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
}
