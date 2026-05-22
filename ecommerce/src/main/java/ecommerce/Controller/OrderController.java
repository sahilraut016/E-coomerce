package ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ecommerce.Model.Order;
import ecommerce.Model.OrderItem;
import ecommerce.Service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins="http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable int userId) {
        return orderService.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable int userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/items/{orderId}")
    public List<OrderItem> getOrderItems(@PathVariable int orderId) {
        return orderService.getOrderItems(orderId);
    }
}
