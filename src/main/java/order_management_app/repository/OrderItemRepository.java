package order_management_app.repository;

import order_management_app.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Query("FROM OrderItem WHERE id = ?1")
    List<OrderItem> findAllById(int id);
}
