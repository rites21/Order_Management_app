package order_management_app.Services;

import order_management_app.DTOs.OrderRequest;
import order_management_app.DTOs.OrderResponse;
import order_management_app.Model.Billing;
import order_management_app.Model.Order;
import order_management_app.Model.OrderItem;
import order_management_app.repository.OrderItemRepository;
import order_management_app.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;
    @Override
    public OrderResponse findById(int id) {
        log.info("Finding order by Id: {}",id);
        Optional<Order> orderResult = orderRepo.findById(id);
        OrderResponse response = new OrderResponse();
        if (orderResult.isPresent()) {
            Order ordResult = orderResult.get();
            response.setCustomerId(ordResult.getCustomerId());
            response.setOrderId(ordResult.getId());
            response.setOrderStatus(ordResult.getStatus());
            response.setTotal(ordResult.getTotal());
            List<OrderItem> orderItemResult = orderItemRepo.findAllById(id);
            if (!orderItemResult.isEmpty()) {
                List<String> itemName = new ArrayList<>();
                List<Integer> quantity = new ArrayList<>();
                for(OrderItem item : orderItemResult) {
                    itemName.add(item.getItemName());
                    quantity.add(item.getItemQuantity());
                }
                response.setItemName(itemName);
                response.setItemQuantity(quantity);
            }
        }else {
            log.error("Order is empty.");
            return null;
        }
        return response;
    }

    @Override
    public Order orderInsert(OrderRequest req) {
            log.info("Inserting Order : {}", req.toString());
            String status = req.getOrderStatus();
            int tax = req.getTax();
            int subtotal = req.getSubtotal();
            int total1 = req.getTotal();
            int total = total1;
            int shipCharge = req.getShippingCharges();
            int customerId = req.getCustomerId();

            List<OrderItem> orderItem = req.getOrderItem();
            Billing billing = req.getBilling();

            Order orderObj = new Order();
            orderObj.setOrderItem(orderItem);
            orderObj.setBilling(billing);
            orderObj.setShippingCharge(shipCharge);
            orderObj.setStatus(status);
            orderObj.setTax(tax);
            orderObj.setSubtotal(subtotal);
            orderObj.setCustomerId(customerId);
            orderObj.setTotal(total);
            orderRepo.save(orderObj);

            return orderObj;
    }

    @Override
    public void insertAll(List<OrderRequest> o) {
        if (o != null) {
            log.info("Inserting Bulk Order");
            for (OrderRequest ord: o) {
                this.orderInsert(ord);
            }
        }
    }

    @Override
    public boolean delete(int id) {
        if(id != 0) {
            log.info("Deleting order by Id: {}",id);
            orderRepo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateAll(List<OrderRequest> o, int id) {
        log.info("Updating list of order of size : {}",o.size());
        for (OrderRequest ord: o) {
            this.update(ord, id);
        }
        return true;
    }

    @Override
    public boolean update(OrderRequest o, int id) {
        log.info("Updating order of of id : {}",id);
        if (orderRepo.existsById(id)) {

            orderRepo.deleteById(id);

            String status = o.getOrderStatus();
            int tax = o.getTax();
            int subtotal = o.getSubtotal();
            int total = o.getTotal();
            int shipCharge = o.getShippingCharges();
            int customerId = o.getCustomerId();

            List<OrderItem> orderItem = o.getOrderItem();
            Billing billing = o.getBilling();

            Order orderObj = new Order(status, subtotal, tax, total, shipCharge, customerId, orderItem, billing);

            orderRepo.save(orderObj);
        }
        else {
            String status = o.getOrderStatus();
            int tax = o.getTax();
            int subtotal = o.getSubtotal();
            int total = o.getTotal();
            int shipCharge = o.getShippingCharges();
            int customerId = o.getCustomerId();

            List<OrderItem> orderItem = o.getOrderItem();
            Billing billing = o.getBilling();

            Order orderObj = new Order(status, subtotal, tax, total, shipCharge, customerId, orderItem, billing);

            orderRepo.save(orderObj);
        }
        return true;
    }
}
