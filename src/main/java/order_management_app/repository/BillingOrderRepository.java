package order_management_app.repository;

import order_management_app.Model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingOrderRepository extends JpaRepository<Billing, Integer> {
}
