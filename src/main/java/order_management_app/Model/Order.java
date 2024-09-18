package order_management_app.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER-DETAILS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private int subtotal;
    private  int tax;
    private int total;
    private LocalDate createdAt;
    private  int shippingCharge;
    private  int customerId;
    @OneToMany
    private List<OrderItem> orderItem;
    @OneToOne
    private Billing billing;

    public Order(String status, int subtotal, int tax, int total, int shipCharge, int customerId, List<OrderItem> orderItem, Billing billing) {
    }
}
