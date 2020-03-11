package application.data.repository;

import application.data.model.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
    @Query(value = "select sum(amount) from dbo_order_product as op join dbo_order as o on op.order_id = o.order_id" +
            " where year(o.created_date) = :nam and o.status > 1 and month(o.created_date) = :th ",nativeQuery = true)
    Integer countAmountOrderProductInAYear(@Param(value = "nam") int nam,@Param(value = "th") int th);

    @Query(value = "select sum(amount) from dbo_order_product as op join dbo_order as o on op.order_id = o.order_id" +
            " where year(o.created_date) = :nam and o.status > 1 and month(o.created_date) = :th and day(o.created_date) = :ng ",nativeQuery = true)
    Integer countAmountOrderProductInAMonth(@Param(value = "nam") int theYear,@Param(value = "th") int th,@Param(value = "ng")int ng);
}
