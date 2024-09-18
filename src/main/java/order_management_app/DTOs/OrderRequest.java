package order_management_app.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import order_management_app.Model.Billing;
import order_management_app.Model.OrderItem;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {
    private String orderStatus;
    private int subtotal;
    private int shippingCharges;
    private int tax;
    private int total;
    private int customerId;

    private List<OrderItem> orderItem;
    private Billing billing;
}
