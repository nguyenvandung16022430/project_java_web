package application.data.repository;

import application.data.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT o FROM dbo_order o " +
            "WHERE (:guid IS NULL OR (o.guid = :guid))" +
            "AND (:userName IS NULL OR (o.userName = :userName))")
    List<Order> findOrderByGuidOrUserName(@Param("guid") String guid, @Param("userName") String userName);

    @Query("SELECT o FROM dbo_order o"+
    " Where o.status = :sort")
    Page<Order> getListOrderByStatusUnconfirmed(Pageable page,@Param("sort") Integer sort);

    @Query("SELECT count(o.id) FROM dbo_order o"+
    " WHERE o.status = 1 ")
    int getAmountOrderUnconfirm();
}
