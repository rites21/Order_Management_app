package order_management_app.Services;

import order_management_app.DTOs.OrderRequest;
import order_management_app.DTOs.OrderResponse;
import order_management_app.Model.Order;

import java.util.List;

public interface OrderService {
    OrderResponse findById(int id);

    Order orderInsert(OrderRequest o);

    void insertAll(List<OrderRequest> o);

    boolean delete(int id);

    boolean update(OrderRequest o, int id);

    boolean updateAll(List<OrderRequest> o, int id);
}
