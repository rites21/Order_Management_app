package order_management_app.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int billId;
    private  String billingAddress1;
    private  String billingAddress2;
    private  String billingCity;
    private  String billingState;
    private int billingZipCode;

}
