package ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.Model.CartItem;
import ecommerce.Model.Order;
import ecommerce.Model.OrderItem;
import ecommerce.Model.Product;
import ecommerce.Repo.OrderItemRepository;
import ecommerce.Repo.OrderRepository;
import ecommerce.Repo.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartService cartService;

    public Order placeOrder(int userId) {

        List<CartItem> cartItems = cartService.getCartItems(userId);

        if (cartItems.size() == 0) {
            return null;
        }

        double total = 0;

        for (CartItem item : cartItems) {
            Product product = productRepo.findById(item.getProductId()).orElse(null);
            if (product != null) {
                total += product.getPrice() * item.getQuantity();
            }
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);

        Order savedOrder = orderRepo.save(order);

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItemRepo.save(orderItem);
        }

        cartService.clearCart(userId);

        return savedOrder;
    }

    public List<Order> getOrdersByUserId(int userId) {
        return orderRepo.findByUserId(userId);
    }

    public List<OrderItem> getOrderItems(int orderId) {
        return orderItemRepo.findByOrderId(orderId);
    }
}
